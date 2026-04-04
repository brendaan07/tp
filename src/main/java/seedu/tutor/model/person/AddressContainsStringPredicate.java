package seedu.tutor.model.person;

import java.util.function.Predicate;

import seedu.tutor.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Address} string contains a specific substring.
 */
public class AddressContainsStringPredicate implements Predicate<Person> {

    private final String string;

    public AddressContainsStringPredicate(String name) {
        this.string = name.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        Address address = person.getAddress();
        if (address.toString().toLowerCase().contains(string)) {
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
        if (!(other instanceof AddressContainsStringPredicate)) {
            return false;
        }

        AddressContainsStringPredicate otherAddressContainsKeywordsPredicate = (AddressContainsStringPredicate) other;
        return string.equals(otherAddressContainsKeywordsPredicate.string);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", string).toString();
    }

}
