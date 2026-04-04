package seedu.tutor.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tutor.testutil.PersonBuilder;

public class EmailContainsStringPredicateTest {

    @Test
    public void equals() {

        String firstPredicateKeyword = "test";
        String secondPredicateKeyword = "test2";

        EmailContainsStringPredicate firstPredicate = new EmailContainsStringPredicate(firstPredicateKeyword);
        EmailContainsStringPredicate secondPredicate = new EmailContainsStringPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        EmailContainsStringPredicate firstPredicateCopy = new EmailContainsStringPredicate(firstPredicateKeyword);
        // same values -> returns true
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different object -> returns false
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_emailContainsKeyword_returnsTrue() {
        Person person = new PersonBuilder().withEmail("bob@fakemail.com").build();
        EmailContainsStringPredicate predicate = new EmailContainsStringPredicate("bob");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate("@");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate(".com");
        assertTrue(predicate.test(person));

    }

    @Test
    public void test_emailContainsKeywordButInDifferentCase_returnsTrue() {
        Person person = new PersonBuilder().withEmail("bob@fakemail.com").build();
        EmailContainsStringPredicate predicate = new EmailContainsStringPredicate("Bob");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate("BOB@FAKEMAIL.COM");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate("bOb@fAkeMail.CoM");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate("bob@fakemail.com");
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_keywordContainsPartialSubstring_returnsTrue() {
        Person person = new PersonBuilder().withEmail("bob@fakemail.com").build();
        EmailContainsStringPredicate predicate = new EmailContainsStringPredicate("ob");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate("fake");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate("bob");
        assertTrue(predicate.test(person));

        predicate = new EmailContainsStringPredicate(".com");
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_relationDoesNotContainKeyword_returnsFalse() {
        Person person = new PersonBuilder().withEmail("bob@fakemail.com").build();
        EmailContainsStringPredicate predicate = new EmailContainsStringPredicate("test");
        assertFalse(predicate.test(person));

        predicate = new EmailContainsStringPredicate("lalalala");
        assertFalse(predicate.test(person));
    }
}
