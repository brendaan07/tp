package seedu.tutor.logic.commands;

import seedu.tutor.commons.core.index.Index;
import seedu.tutor.commons.util.ToStringBuilder;
import seedu.tutor.logic.Messages;
import seedu.tutor.logic.commands.exceptions.CommandException;
import seedu.tutor.model.Model;
import seedu.tutor.model.person.*;
import seedu.tutor.model.relation.Relation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class RelateAddCommand extends RelateCommand {

    private final Index index;
    private final Relation relationToAdd;

    /**
     * Creates a RelateAddCommand.
     * This constructor is package-private to restrict creation
     * to the command factory in {@link RelateCommand}.
     */
    RelateAddCommand(Index index, Relation relationToAdd) {
        requireNonNull(index);
        requireNonNull(relationToAdd);

        this.index = index;
        this.relationToAdd = relationToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddRelation = lastShownList.get(index.getZeroBased());
        Person addedRelationPerson = createAddRelationPerson(personToAddRelation, relationToAdd);
        model.setPerson(personToAddRelation, addedRelationPerson);
        return new CommandResult(String.format(MESSAGE_RELATE_SUCCESS, Messages.format(addedRelationPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToAddRelation}
     */
    private static Person createAddRelationPerson(Person personToAddRelation, Relation relationToAdd) {
        assert personToAddRelation != null;

        Set<Relation> updatedRelations = new HashSet<>(personToAddRelation.getRelations()); // Original relations
        updatedRelations.add(relationToAdd);

        return new Person(
                personToAddRelation.getName(),
                personToAddRelation.getPhone(),
                personToAddRelation.getEmail(),
                personToAddRelation.getAddress(),
                personToAddRelation.getTags(),
                updatedRelations,
                personToAddRelation.getSubject()
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RelateAddCommand)) {
            return false;
        }

        RelateAddCommand otherRelateAddCommand = (RelateAddCommand) other;
        return index.equals(otherRelateAddCommand.index)
                && relationToAdd.equals(otherRelateAddCommand.relationToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("relationToAdd", relationToAdd)
                .toString();
    }
}
