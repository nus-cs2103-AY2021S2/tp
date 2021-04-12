package seedu.partyplanet.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.testutil.PersonBuilder;

public class TagsContainsExactCaseTagPredicateTest {

    @Test
    public void equals() {
        String firstPredicateTag = "first";
        String secondPredicateTag = "second";

        TagsContainsExactCaseTagPredicate firstPredicate = new TagsContainsExactCaseTagPredicate(firstPredicateTag);
        TagsContainsExactCaseTagPredicate secondPredicate = new TagsContainsExactCaseTagPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsExactCaseTagPredicate firstPredicateCopy = new TagsContainsExactCaseTagPredicate(firstPredicateTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsExactCaseTag_returnsTrue() {
        TagsContainsExactCaseTagPredicate predicate = new TagsContainsExactCaseTagPredicate("Friend");
        // Exact Case
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("Friend").build()));
        // Extra tag
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("Friend", "abc").build()));
    }

    @Test
    public void test_tagsDoesNotContainExactCaseTag_returnsFalse() {
        TagsContainsExactCaseTagPredicate predicate = new TagsContainsExactCaseTagPredicate("Friend");
        // Exact different Case
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("FrIeNd").build()));
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
