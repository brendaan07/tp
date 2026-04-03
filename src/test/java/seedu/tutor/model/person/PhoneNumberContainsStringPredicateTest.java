package seedu.tutor.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tutor.testutil.PersonBuilder;

public class PhoneNumberContainsStringPredicateTest {

    @Test
    public void equals() {

        String firstPredicateKeyword = "123";
        String secondPredicateKeyword = "124";

        PhoneNumberContainsStringPredicate firstPredicate =
                new PhoneNumberContainsStringPredicate(firstPredicateKeyword);
        PhoneNumberContainsStringPredicate secondPredicate =
                new PhoneNumberContainsStringPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        PhoneNumberContainsStringPredicate firstPredicateCopy =
                new PhoneNumberContainsStringPredicate(firstPredicateKeyword);
        // same values -> returns true
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different object -> returns false
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_phoneNumberContainsKeyword_returnsTrue() {
        Person person = new PersonBuilder().withPhone("98765432").build();
        PhoneNumberContainsStringPredicate predicate = new PhoneNumberContainsStringPredicate("98765432");
        assertTrue(predicate.test(person));

        predicate = new PhoneNumberContainsStringPredicate("9");
        assertTrue(predicate.test(person));

        predicate = new PhoneNumberContainsStringPredicate("432");
        assertTrue(predicate.test(person));

    }

    @Test
    public void test_phoneNumberDoesNotContainKeyword_returnsFalse() {
        Person person = new PersonBuilder().withPhone("123").build();
        PhoneNumberContainsStringPredicate predicate = new PhoneNumberContainsStringPredicate("456");
        assertFalse(predicate.test(person));

        predicate = new PhoneNumberContainsStringPredicate("9");
        assertFalse(predicate.test(person));
    }

}
