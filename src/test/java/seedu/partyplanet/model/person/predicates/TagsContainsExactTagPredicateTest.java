package seedu.partyplanet.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.testutil.PersonBuilder;

public class TagsContainsExactTagPredicateTest {

    @Test
    public void equals() {
        String firstPredicateTag = "first";
        String secondPredicateTag = "second";

        TagsContainsExactTagPredicate firstPredicate = new TagsContainsExactTagPredicate(firstPredicateTag);
        TagsContainsExactTagPredicate secondPredicate = new TagsContainsExactTagPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsExactTagPredicate firstPredicateCopy = new TagsContainsExactTagPredicate(firstPredicateTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsExactTag_returnsTrue() {
        TagsContainsExactTagPredicate predicate = new TagsContainsExactTagPredicate("Friend");
        // Exact Case
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("Friend").build()));
        // Extra tag
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("Friend", "abc").build()));
        // Exact different Case
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("FrIeNd").build()));
    }

    @Test
    public void test_tagsDoesNotContainExactTag_returnsFalse() {
        TagsContainsExactTagPredicate predicate = new TagsContainsExactTagPredicate("Friend");
        // Longer
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("bestFriend").build()));
        // Different word
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("noMatch").build()));
        // Shorter
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("Fri").build()));
        // No tag
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
    }
}
