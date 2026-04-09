package seedu.tutor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tutor.testutil.PersonBuilder;

public class TagContainsStringPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "friend";
        String secondPredicateKeyword = "study";

        TagContainsStringPredicate firstPredicate = new TagContainsStringPredicate(firstPredicateKeyword);
        TagContainsStringPredicate secondPredicate = new TagContainsStringPredicate(secondPredicateKeyword);

        assertTrue(firstPredicate.equals(firstPredicate));

        TagContainsStringPredicate firstPredicateCopy = new TagContainsStringPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeyword_returnsTrue() {
        Person person = new PersonBuilder().withTags("friend").build();
        TagContainsStringPredicate predicate = new TagContainsStringPredicate("friend");
        assertTrue(predicate.test(person));

        predicate = new TagContainsStringPredicate("rie");
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_tagContainsKeywordButInDifferentCase_returnsTrue() {
        Person person = new PersonBuilder().withTags("StudyBuddy").build();
        TagContainsStringPredicate predicate = new TagContainsStringPredicate("studybuddy");
        assertTrue(predicate.test(person));

        predicate = new TagContainsStringPredicate("STUDY");
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_tagDoesNotContainKeyword_returnsFalse() {
        Person person = new PersonBuilder().withTags("friend").build();
        TagContainsStringPredicate predicate = new TagContainsStringPredicate("study");
        assertFalse(predicate.test(person));

        Person personWithoutTag = new PersonBuilder().build();
        predicate = new TagContainsStringPredicate("friend");
        assertFalse(predicate.test(personWithoutTag));
    }

    @Test
    public void toStringMethod() {
        TagContainsStringPredicate predicate = new TagContainsStringPredicate("Friend");
        String expected = TagContainsStringPredicate.class.getCanonicalName() + "{keyword=friend}";
        assertEquals(expected, predicate.toString());
    }
}

