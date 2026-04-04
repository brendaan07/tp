package seedu.tutor.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tutor.testutil.PersonBuilder;

public class AddressContainsStringPredicateTest {

    @Test
    public void equals() {

        String firstPredicateKeyword = "test";
        String secondPredicateKeyword = "test2";

        AddressContainsStringPredicate firstPredicate = new AddressContainsStringPredicate(firstPredicateKeyword);
        AddressContainsStringPredicate secondPredicate = new AddressContainsStringPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        AddressContainsStringPredicate firstPredicateCopy = new AddressContainsStringPredicate(firstPredicateKeyword);
        // same values -> returns true
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different object -> returns false
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_addressContainsKeyword_returnsTrue() {
        Person person = new PersonBuilder().withAddress("21 Lower Kent Ridge Road Singapore 119077").build();
        AddressContainsStringPredicate predicate = new AddressContainsStringPredicate("Road");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("21");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("Singapore");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("Kent Ridge");
        assertTrue(predicate.test(person));

    }

    @Test
    public void test_addressContainsKeywordButInDifferentCase_returnsTrue() {
        Person person = new PersonBuilder().withAddress("21 Lower Kent Ridge Road Singapore 119077").build();
        AddressContainsStringPredicate predicate = new AddressContainsStringPredicate("lower");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("LOWER");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("LoWEr");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("ROAD SINGAPORE");
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_keywordContainsPartialSubstring_returnsTrue() {
        Person person = new PersonBuilder().withAddress("21 Lower Kent Ridge Road Singapore 119077").build();
        AddressContainsStringPredicate predicate = new AddressContainsStringPredicate("119");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("Lower K");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("21");
        assertTrue(predicate.test(person));

        predicate = new AddressContainsStringPredicate("Singap");
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_relationDoesNotContainKeyword_returnsFalse() {
        Person person = new PersonBuilder().withAddress("21 Lower Kent Ridge Road Singapore 119077").build();
        AddressContainsStringPredicate predicate = new AddressContainsStringPredicate("test");
        assertFalse(predicate.test(person));

        predicate = new AddressContainsStringPredicate("lalalala");
        assertFalse(predicate.test(person));
    }
}
