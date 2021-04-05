package seedu.partyplanet.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.testutil.PersonBuilder;

public class TagsContainsTagPredicateTest {

    @Test
    public void equals() {
        String firstPredicateTag = "first";
        String secondPredicateTag = "second";

        TagsContainsTagPredicate firstPredicate = new TagsContainsTagPredicate(firstPredicateTag);
        TagsContainsTagPredicate secondPredicate = new TagsContainsTagPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsTagPredicate firstPredicateCopy = new TagsContainsTagPredicate(firstPredicateTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsTag_returnsTrue() {
        TagsContainsTagPredicate predicate = new TagsContainsTagPredicate("Friend");
        // Exact Case
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("Friend").build()));
        // Exact different Case
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("FrIeNd").build()));
        // Longer
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("bestfriend").build()));
        // Extra tag
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("Friend", "abc").build()));
    }

    @Test
    public void test_tagsDoesNotContainTag_returnsFalse() {
        TagsContainsTagPredicate predicate = new TagsContainsTagPredicate("Friend");
        // Different word
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("noMatch").build()));
        // Shorter
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("Fri").build()));
        // No tag
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
    }
}
