package seedu.tutor.logic.parser;

import static seedu.tutor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tutor.logic.commands.ClearCommand;
import seedu.tutor.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        if (!args.strip().equals("confirm")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        } else {
            return new ClearCommand();
        }
    }
}
