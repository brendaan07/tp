package seedu.tutor.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.tutor.model.relation.Relation;

/**
 * Tests that a {@code Person}'s {@code Relation} string contains a specific substring.
 */
public class RelationContainsStringPredicate implements Predicate<Person> {

    private final String string;

    public RelationContainsStringPredicate(String name) {
        this.string = name;
    }

    @Override
    public boolean test(Person person) {
        Set<Relation> relations = person.getRelations();
        for (Relation r : relations) {
            if (r.relationName.contains(string)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RelationContainsStringPredicate)) {
            return false;
        }

        RelationContainsStringPredicate otherNameContainsKeywordsPredicate = (RelationContainsStringPredicate) other;
        return string.equals(otherNameContainsKeywordsPredicate.string);
    }

    @Override
    public String toString() {
        return string;
    }

}
