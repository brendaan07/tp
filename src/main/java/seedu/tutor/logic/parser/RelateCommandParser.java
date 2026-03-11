package seedu.tutor.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tutor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_RELATE_ADD;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_RELATE_DELETE;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_RELATION;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;

import seedu.tutor.commons.core.index.Index;
import seedu.tutor.logic.commands.RelateCommand;
import seedu.tutor.logic.parser.exceptions.ParseException;
import seedu.tutor.model.relation.Relation;

/**
 * Parses input arguments and returns a new RelateCommand object
 */
public class RelateCommandParser implements Parser<RelateCommand> {

    private static final Map<Prefix, RelateCommand.RelateType> relateCommandTypeMap = Map.of(
            PREFIX_RELATE_ADD, RelateCommand.RelateType.ADD,
            PREFIX_RELATE_DELETE, RelateCommand.RelateType.DELETE
    );

    /**
     * Parses the given {@code String} of arguments in the context of the RelateCommand
     * and returns a RelateCommand object for execution.
     * @param args The full user's input.
     * @return A RelateCommand object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RelateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RELATE_ADD, PREFIX_RELATE_DELETE);

        Index index;
        Relation relation;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RelateCommand.MESSAGE_USAGE), pe);
        }

        // errors
        if (argMultimap.getValue(PREFIX_NAME).isPresent()
                || argMultimap.getValue(PREFIX_EMAIL).isPresent()
                || argMultimap.getValue(PREFIX_PHONE).isPresent()
                || argMultimap.getValue(PREFIX_ADDRESS).isPresent()
                || argMultimap.getValue(PREFIX_TAG).isPresent()
                || argMultimap.getValue(PREFIX_RELATION).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RelateCommand.MESSAGE_USAGE));
        }

        // currently assume only one operation per command
        // can expand to add and/or delete of multiple relation per command
        if (argMultimap.getValue(PREFIX_RELATE_ADD).isPresent()) {
            relation = ParserUtil.parseRelation(argMultimap.getValue(PREFIX_RELATE_ADD).get());
            return RelateCommand.create(index, relateCommandTypeMap.get(PREFIX_RELATE_ADD), relation);
        } else if (argMultimap.getValue(PREFIX_RELATE_DELETE).isPresent()) {
            relation = ParserUtil.parseRelation(argMultimap.getValue(PREFIX_RELATE_DELETE).get());
            return RelateCommand.create(index, relateCommandTypeMap.get(PREFIX_RELATE_DELETE), relation);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RelateCommand.MESSAGE_USAGE));
        }
    }
}
