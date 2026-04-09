package seedu.tutor.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tutor.model.Model;
import seedu.tutor.model.TutorMap;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears the entire list.\n"
            + "Parameters: confirm\n"
            + "Example: " + COMMAND_WORD + " confirm\n"

            + "Notes: \n"
            + "⚠ This action is irreversible! Type \"clear confirm\" to proceed with clearing.";


    public static final String MESSAGE_SUCCESS = "TutorMap has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTutorMap(new TutorMap());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
