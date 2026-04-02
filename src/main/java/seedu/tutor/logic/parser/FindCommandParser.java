package seedu.tutor.logic.parser;

import static seedu.tutor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.tutor.logic.commands.FindCommand;
import seedu.tutor.logic.parser.exceptions.ParseException;
import seedu.tutor.model.person.AddressContainsStringPredicate;
import seedu.tutor.model.person.NameContainsKeywordsPredicate;
import seedu.tutor.model.person.RelationContainsStringPredicate;
import seedu.tutor.model.person.SubjectContainsStringPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.startsWith("r/")) {
            String trimmed = trimmedArgs.substring(2).trim();
            String slashRegex = "[ /]+$";
            if (trimmed.isEmpty() || trimmed.matches(slashRegex)) {
                throw new ParseException("Keyword missing! Please specify a non-space, non-slash keyword after 'r/' \n"
                        + "Example: find r/Alex Yeoh, find r/parent");
            }

            return new FindCommand(new RelationContainsStringPredicate(trimmed));
        }

        if (trimmedArgs.startsWith("s/")) {
            String trimmed = trimmedArgs.substring(2).trim();
            String slashRegex = "[ /]+$";
            if (trimmed.isEmpty() || trimmed.matches(slashRegex)) {
                throw new ParseException("Keyword missing! Please specify a non-space, "
                        + "non-slash keyword (subject) after 's/' \n"
                        + "Example: find s/Math, find s/Science");
            }

            return new FindCommand(new SubjectContainsStringPredicate(trimmed));
        }

        if (trimmedArgs.startsWith("a/")) {

            String trimmed = trimmedArgs.substring(2).trim();
            String slashRegex = "[ /]+$";
            if (trimmed.isEmpty() || trimmed.matches(slashRegex)) {
                throw new ParseException("Keyword missing! Please specify a non-space, "
                        + "non-slash keyword (address) after 'a/' \n"
                        + "Example: find a/Woodlands, find a/Blk");
            }

            return new FindCommand(new AddressContainsStringPredicate(trimmed));

        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
