package seedu.tutor.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.tutor.commons.core.index.Index;
import seedu.tutor.logic.Messages;
import seedu.tutor.logic.commands.exceptions.CommandException;
import seedu.tutor.logic.parser.EditCommandParser;
import seedu.tutor.logic.parser.exceptions.ParseException;
import seedu.tutor.model.Model;
import seedu.tutor.model.label.Label;
import seedu.tutor.model.person.Person;

/**
 * Edit a person's subject field using xor operation.
 */
public class EditSubjectCommand extends Command {

    private final Index index;
    private final Label[] deltaLabels;
    private final EditCommandParser parser = new EditCommandParser();

    /**
     * Returns a EditSubjectCommand object which edits a peron's subject field with xor operation.
     * @param index The index of the person to be edited.
     * @param deltaLabels The subjects to be added or removed.
     */
    public EditSubjectCommand(Index index, Label[] deltaLabels) {
        this.index = index;
        this.deltaLabels = deltaLabels;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personEditSubject = lastShownList.get(index.getZeroBased());
        Set<Label> oldSubjects = personEditSubject.getSubjects();
        Set<Label> newSubjects = new HashSet<>(oldSubjects);

        for (Label delta: deltaLabels) {
            if (oldSubjects.contains(delta)) {
                newSubjects.remove(delta);
            } else {
                newSubjects.add(delta);
            }
        }

        Set<String> args = new HashSet<>();
        for (Label l: newSubjects) {
            args.add(l.labelName);
        }

        StringBuilder input = new StringBuilder(" " + this.index.getOneBased());
        for (String s: args) {
            input.append(" s/");
            input.append(s);
        }

        if (args.isEmpty()) {
            input.append(" s/");
        }

        EditCommand command;
        try {
            command = parser.parse(input.toString());
        } catch (ParseException pe) {
            throw new CommandException("Unknown error by EditSubjectCommand");
        }

        return command.execute(model);
    }
}
