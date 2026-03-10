package seedu.tutor.logic.commands;

/**
 * Creates RelateAddCommand or RelateDeleteCommand
 */
public abstract class RelateCommand extends Command{

    /**
     * A factory for the subtypes.
     * @param type Type of {@code RelationCommand} that is intended by the user.
     * @param details A string of relation.
     * @return Either of the subtypes, {@code RelateAddCommand} or {@code RelateDeleteCommand}.
     */
    public static RelateCommand create(Character type, String details) {
        if (type.equals('a')) {
            return new RelateAddCommand(details);
        } else if (type.equals('d')) {
            return new RelateDeleteCommand(details);
        } else {
            // should not reach here
            return null;
        }
    }
}
