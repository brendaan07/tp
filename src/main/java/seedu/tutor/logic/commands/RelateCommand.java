package seedu.tutor.logic.commands;

import static seedu.tutor.logic.parser.CliSyntax.PREFIX_RELATE_ADD;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_RELATE_DELETE;

import seedu.tutor.commons.core.index.Index;
import seedu.tutor.model.relation.Relation;


/**
 * Creates subtype of RelateCommand.
 */
public abstract class RelateCommand extends Command {

    public enum RelateType {
        ADD, DELETE
    }

    public static final String COMMAND_WORD = "relate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the relations of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_RELATE_ADD + "RELATION] "
            + "or "
            + "[" + PREFIX_RELATE_DELETE + "RELATION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RELATE_ADD + "Linq/Keiran/teammate/teammate ";

    /**
     * A factory for the creation of subtypes of RelateCommand.
     * @param type Type of {@code RelationCommand} that is intended by the user.
     * @param relation The relation object between two contacts.
     * @return Subtype of RelateCommand.
     */
    public static RelateCommand create(Index index, RelateType type, Relation relation) {
        switch (type) {

        case ADD -> {
            return new RelateAddCommand(index, relation);
        }

        case DELETE -> {
            return new RelateDeleteCommand(index, relation);
        }

        default -> {
            throw new IllegalArgumentException();
        }

        }
    }
}
