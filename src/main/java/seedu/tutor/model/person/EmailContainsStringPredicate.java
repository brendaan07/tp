package seedu.tutor.model.person;

import java.util.function.Predicate;

import seedu.tutor.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Email} string contains a specific substring.
 */
public class EmailContainsStringPredicate implements Predicate<Person> {

    private final String string;

    public EmailContainsStringPredicate(String name) {
        this.string = name.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        Email email = person.getEmail();
        if (email.toString().contains(string)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmailContainsStringPredicate)) {
            return false;
        }

        EmailContainsStringPredicate otherNameContainsKeywordsPredicate = (EmailContainsStringPredicate) other;
        return string.equals(otherNameContainsKeywordsPredicate.string);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", string).toString();
    }


}
