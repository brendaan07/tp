package seedu.tutor.logic.parser;

import seedu.tutor.commons.core.index.Index;
import seedu.tutor.logic.commands.RelateCommand;
import seedu.tutor.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.tutor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tutor.logic.parser.CliSyntax.*;

public class RelateCommandParser {

    public RelateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RELATE_ADD, PREFIX_RELATE_DELETE);

        Index index;
        String details;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RelateCommand.MESSAGE_USAGE), pe);
        }

        // currently assume only one operation per command
        // assume parseRelation exists
        if (argMultimap.getValue(PREFIX_RELATE_ADD).isPresent()) {
            details = ParserUtil.parseRelation(argMultimap.getValue(PREFIX_RELATE_ADD).get());
            return RelateCommand.create(index, "add", details);
        } else if (argMultimap.getValue(PREFIX_RELATE_DELETE).isPresent()) {
            details = ParserUtil.parseRelation(argMultimap.getValue(PREFIX_RELATE_DELETE).get());
            return RelateCommand.create(index, "delete", details);
        } else {
            // error handling here?
            return null;
        }
    }
}
