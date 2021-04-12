package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ContactBuilder;

/**
 * Contains unit tests for {@code ContactTagsContainKeywordsPredicate}.
 */
public class ContactTagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        ContactTagsContainKeywordsPredicate firstPredicate =
                new ContactTagsContainKeywordsPredicate(firstPredicateKeywordList);
        ContactTagsContainKeywordsPredicate secondPredicate =
                new ContactTagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactTagsContainKeywordsPredicate firstPredicateCopy =
                new ContactTagsContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different contact -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_contactTagsContainKeywords_returnsTrue() {
        ContactTagsContainKeywordsPredicate predicate;

        // Zero keywords
        predicate = new ContactTagsContainKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new ContactBuilder().withTags("friends").build()));

        // One keyword
        predicate = new ContactTagsContainKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new ContactBuilder().withTags("friends", "owesMoney").build()));

        // Multiple keywords
        predicate = new ContactTagsContainKeywordsPredicate(Arrays.asList("friends", "owesMoney"));
        assertTrue(predicate.test(new ContactBuilder().withTags("friends", "owesMoney").build()));

        // Mixed-case keywords
        predicate = new ContactTagsContainKeywordsPredicate(Arrays.asList("fRieNds", "oWesmOneY"));
        assertTrue(predicate.test(new ContactBuilder().withTags("friends", "owesMoney").build()));
    }

    @Test
    public void test_contactTagsDoesNotContainKeywords_returnsFalse() {
        ContactTagsContainKeywordsPredicate predicate;

        // Non-matching keyword
        predicate = new ContactTagsContainKeywordsPredicate(Arrays.asList("colleagues"));
        assertFalse(predicate.test(new ContactBuilder().withTags("friends", "owesMoney").build()));

        // Only one matching keyword
        predicate = new ContactTagsContainKeywordsPredicate(Arrays.asList("colleagues", "friends"));
        assertFalse(predicate.test(new ContactBuilder().withTags("colleagues", "owesMoney").build()));
    }
}
