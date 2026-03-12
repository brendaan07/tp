package seedu.tutor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_RELATE_ADD;
import static seedu.tutor.logic.parser.CliSyntax.PREFIX_RELATE_DELETE;

import seedu.tutor.commons.core.index.Index;
import seedu.tutor.model.relation.Relation;


/**
 * Creates subtype of RelateCommand.
 */
public abstract class RelateCommand extends Command {

    /**
     * enum for types of RelateCommand
     */
    public enum RelateCommandType {
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

    public static final String MESSAGE_RELATE_SUCCESS = "Updated relation(s) to Person: %1$s";

    /**
     * A factory for the creation of subtypes of RelateCommand.
     * @param type Type of {@code RelationCommand} that is intended by the user.
     * @param relation The relation object between two contacts.
     * @return Subtype of RelateCommand.
     */
    public static RelateCommand create(Index index, RelateCommandType type, Relation relation) {

        requireNonNull(index);
        requireNonNull(type);
        requireNonNull(relation);

        switch (type) {

        case ADD -> {
            return new RelateAddCommand(index, relation);
        }

        case DELETE -> {
            return new RelateDeleteCommand(index, relation);
        }

        default -> {
            // should not reach here
            return null;
        }

        }
    }
}
