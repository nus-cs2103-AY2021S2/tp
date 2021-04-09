package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EntryBuilder;

public class EntryNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EntryNameContainsKeywordsPredicate firstPredicate =
                new EntryNameContainsKeywordsPredicate(firstPredicateKeywordList);
        EntryNameContainsKeywordsPredicate secondPredicate =
                new EntryNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EntryNameContainsKeywordsPredicate firstPredicateCopy =
                new EntryNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_entryNameContainsKeywords_returnsTrue() {
        // One keyword
        EntryNameContainsKeywordsPredicate predicate = new
                EntryNameContainsKeywordsPredicate(Collections.singletonList("Consultation"));
        assertTrue(predicate.test(new EntryBuilder().build()));

        // Multiple keywords
        predicate = new EntryNameContainsKeywordsPredicate(Arrays.asList("Final", "Exam"));
        assertTrue(predicate.test(new EntryBuilder().withEntryName("Final Exam lol").build()));

        // Mixed-case keywords
        predicate = new EntryNameContainsKeywordsPredicate(Arrays.asList("final", "exam"));
        assertTrue(predicate.test(new EntryBuilder().withEntryName("Final Exam now").build()));
    }

    @Test
    public void test_entryNameDoesNotContainKeywords_returnFalse() {
        // Non-matching keyword
        EntryNameContainsKeywordsPredicate predicate = new
                EntryNameContainsKeywordsPredicate(Collections.singletonList("Consultation"));
        assertFalse(predicate.test(new EntryBuilder().withEntryName("Nope").build()));

        // Only one matching keyword
        predicate = new EntryNameContainsKeywordsPredicate(Arrays.asList("Final", "Exam"));
        assertFalse(predicate.test(new EntryBuilder().withEntryName("Final quiz").build()));
    }
}
