package seedu.tutor.model.person;

import java.util.function.Predicate;

import seedu.tutor.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Phone} string contains a specific substring.
 */
public class PhoneNumberContainsStringPredicate implements Predicate<Person> {

    private final String string;

    public PhoneNumberContainsStringPredicate(String number) {
        this.string = number.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        Phone phone = person.getPhone();

        if (phone.toString().contains(string)) {
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
        if (!(other instanceof PhoneNumberContainsStringPredicate)) {
            return false;
        }

        PhoneNumberContainsStringPredicate otherNumberContainsKeywordsPredicate =
                (PhoneNumberContainsStringPredicate) other;
        return string.equals(otherNumberContainsKeywordsPredicate.string);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", string).toString();
    }


}
