package seedu.tutor.model.person;

import java.util.Set;
import java.util.function.Predicate;

import seedu.tutor.commons.util.ToStringBuilder;
import seedu.tutor.model.label.Label;

/**
 * Tests that a {@code Person}'s {@code Tag} string contains a specific substring.
 */
public class TagContainsStringPredicate implements Predicate<Person> {

    private final String string;

    public TagContainsStringPredicate(String name) {
        this.string = name.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        Set<Label> tags = person.getTags();
        for (Label tag : tags) {
            if (tag.labelName.toLowerCase().contains(string.toLowerCase())) {
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
        if (!(other instanceof TagContainsStringPredicate)) {
            return false;
        }

        TagContainsStringPredicate otherNameContainsKeywordsPredicate = (TagContainsStringPredicate) other;
        return string.equals(otherNameContainsKeywordsPredicate.string);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", string).toString();
    }
}

