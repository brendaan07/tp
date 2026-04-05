package seedu.tutor.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.tutor.commons.core.index.Index;
import seedu.tutor.logic.Messages;
import seedu.tutor.logic.commands.exceptions.CommandException;
import seedu.tutor.model.Model;
import seedu.tutor.model.label.Label;
import seedu.tutor.model.person.Person;

/**
 * Edits a person's subject field using xor operation.
 */
public class SubjectEditCommand extends Command {

    private final Index index;
    private final Label[] subjectsToEdits;

    /**
     * Returns an EditSubjectCommand object that adds a subject if it doesn't exist,
     * or removes it if it exists in the Person's Subject field.
     * @param index The index of the person to be edited.
     * @param subjectsToEdits The subjects to be added or removed.
     */
    protected SubjectEditCommand(Index index, Label[] subjectsToEdits) {
        requireNonNull(index);
        requireNonNull(subjectsToEdits);
        this.index = index;
        this.subjectsToEdits = subjectsToEdits;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEditSubject = lastShownList.get(index.getZeroBased());
        Set<Label> oldSubjects = personToEditSubject.getSubjects();
        Set<Label> mutableOldSubjects = new HashSet<>(oldSubjects);
        Set<Label> newSubjects = subjectsXorOperation(mutableOldSubjects,
                new HashSet<>(Arrays.asList(subjectsToEdits)));

        Person editedPerson = createEditSubjectPerson(personToEditSubject, newSubjects);
        model.setPerson(personToEditSubject, editedPerson);

        StringBuilder result = new StringBuilder("Edited " + editedPerson.getName()
                + "'s subject field, now contains: ");
        for (Label subject: editedPerson.getSubjects()) {
            result.append(subject.labelName);
            result.append(" ");
        }

        if (!editedPerson.getSubjects().isEmpty()) {
            return new CommandResult(result.toString());
        } else {
            return new CommandResult(editedPerson.getName() + "'s subject field has been emptied.");
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEditSubject}
     */
    private static Person createEditSubjectPerson(Person personToEditSubject, Set<Label> newSubjects) {
        requireNonNull(personToEditSubject);
        requireNonNull(newSubjects);

        return new Person(
                personToEditSubject.getName(),
                personToEditSubject.getPhone(),
                personToEditSubject.getEmail(),
                personToEditSubject.getAddress(),
                personToEditSubject.getTags(),
                personToEditSubject.getRelations(),
                newSubjects
        );
    }

    /**
     * Merges two Collection objects using xor operation.
     * @param collection1 The first Collection object.
     * @param collection2 The second Collection object.
     * @return The merge result in a Set object.
     */
    private static Set<Label> subjectsXorOperation(Collection<Label> collection1,
                                                   Collection<Label> collection2) {
        for (Label subject: collection2) {
            if (collection1.contains(subject)) {
                collection1.remove(subject);
            } else {
                collection1.add(subject);
            }
        }

        return new HashSet<>(collection1);
    }
}
