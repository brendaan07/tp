package seedu.tutor.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.tutor.logic.commands.exceptions.CommandException;
import seedu.tutor.logic.parser.EditCommandParser;
import seedu.tutor.logic.parser.exceptions.ParseException;
import seedu.tutor.model.Model;
import seedu.tutor.model.label.Label;
import seedu.tutor.model.person.Person;

/**
 * Delete subject/s across all person
 */
public class DeleteSubjectCommand extends Command {

    private final Label[] labelsToDelete;
    private final EditCommandParser parser = new EditCommandParser();

    /**
     * Return a DeleteSubjectCommand object that deletes subject's across all person.
     * @param labelsToDelete An array of subject/s as Label object to be deleted.
     */
    public DeleteSubjectCommand(Label[] labelsToDelete) {
        this.labelsToDelete = labelsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        List<Person> persons = model.getTutorMap().getPersonList();
        List<Command> editCommands = new ArrayList<>();

        for (int index = 0; index < persons.size(); index++) {
            Person person = persons.get(index);
            Set<Label> oldLabels = new HashSet<>(person.getSubjects());
            for (Label toDelete: labelsToDelete) {
                oldLabels.remove(toDelete);
            }
            if (oldLabels.size() == person.getSubjects().size()) {
                // nothing changed
                continue;
            }
            Set<String> args = new HashSet<>();
            for (Label label: oldLabels) {
                args.add(label.labelName);
            }
            StringBuilder input = new StringBuilder(" " + (index + 1));
            for (String subject: args) {
                input.append(" s/");
                input.append(subject);
            }

            if (args.isEmpty()) {
                input.append(" s/");
            }

            EditCommand editCommand;
            try {
                editCommand = parser.parse(input.toString());
            } catch (ParseException pe) {
                throw new CommandException("Unknown error, by ChangeSubjectCommand");
            }
            editCommands.add(editCommand);
        }

        CommandResult commandResult = null;
        for (Command editCommand: editCommands) {
            CommandResult temp = editCommand.execute(model);
            commandResult = CommandResult.merge(commandResult, temp);
        }

        if (commandResult == null) {
            throw new CommandException("Subject/s does not exist");
        } else {
            return commandResult;
        }
    }
}
