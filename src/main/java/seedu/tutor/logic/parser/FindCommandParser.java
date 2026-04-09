package seedu.tutor.logic.parser;

import static seedu.tutor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.tutor.logic.commands.FindCommand;
import seedu.tutor.logic.parser.exceptions.ParseException;
import seedu.tutor.model.person.AddressContainsStringPredicate;
import seedu.tutor.model.person.EmailContainsStringPredicate;
import seedu.tutor.model.person.NameContainsKeywordsPredicate;
import seedu.tutor.model.person.PhoneNumberContainsStringPredicate;
import seedu.tutor.model.person.RelationContainsStringPredicate;
import seedu.tutor.model.person.SubjectContainsStringPredicate;
import seedu.tutor.model.person.TagContainsStringPredicate;

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

        if (trimmedArgs.startsWith("p/")) {
            String trimmed = trimmedArgs.substring(2).trim();
            String slashRegex = "[ /]+$";
            String numberRegex = "^[0-9]+$";
            if (trimmed.isEmpty() || trimmed.matches(slashRegex)) {
                throw new ParseException("Keyword missing! Please specify a non-space, non-slash keyword after 'p/' \n"
                        + "Example: find p/12345678");
            }

            if (!trimmed.matches(numberRegex)) {
                throw new ParseException("Input is not a valid number. "
                        + "Please enter numbers with no special characters.");
            }

            return new FindCommand(new PhoneNumberContainsStringPredicate(trimmed));
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

        if (trimmedArgs.startsWith("t/")) {
            String trimmed = trimmedArgs.substring(2).trim();
            String slashRegex = "[ /]+$";
            if (trimmed.isEmpty() || trimmed.matches(slashRegex)) {
                throw new ParseException("Keyword missing! Please specify a non-space, "
                        + "non-slash keyword (tag) after 't/' \n"
                        + "Example: find t/friend, find t/homework");
            }

            return new FindCommand(new TagContainsStringPredicate(trimmed));
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

        if (trimmedArgs.startsWith("n/")) {
            String trimmed = trimmedArgs.substring(2).trim();
            String slashRegex = "[ /]+$";
            if (trimmed.isEmpty() || trimmed.matches(slashRegex)) {
                throw new ParseException("Keyword missing! Please specify a non-space, "
                        + "non-slash keyword (name) after 'n/' \n"
                        + "Example: find n/Bob, find n/Alice Bob");
            }

            String[] nameKeywords = trimmed.split("\\s+");

            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (trimmedArgs.startsWith("e/")) {
            String trimmed = trimmedArgs.substring(2).trim();
            String slashRegex = "[ /]+$";
            if (trimmed.isEmpty() || trimmed.matches(slashRegex)) {
                throw new ParseException("Keyword missing! Please specify a non-space, "
                        + "non-slash keyword (email) after 'e/' \n"
                        + "Example: find e/gmail, find e/alexyeoh123@fakemail.com");
            }

            return new FindCommand(new EmailContainsStringPredicate(trimmed));
        }

        throw new ParseException("Prefix missing! Find must be followed by either "
                + "'n/', 'p/', 'e/', 'a/',  's/', 'r/' or 't/' "
                + "depending on what field is being searched for.");
    }

}
