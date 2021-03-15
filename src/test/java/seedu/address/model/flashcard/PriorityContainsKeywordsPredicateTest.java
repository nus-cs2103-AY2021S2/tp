package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class PriorityContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PriorityContainsKeywordsPredicate firstPredicate =
                new PriorityContainsKeywordsPredicate(firstPredicateKeywordList);
        PriorityContainsKeywordsPredicate secondPredicate =
                new PriorityContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PriorityContainsKeywordsPredicate firstPredicateCopy =
                new PriorityContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different object -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_priorityContainsKeywords_returnsTrue() {
        // One keyword
        PriorityContainsKeywordsPredicate predicate =
                new PriorityContainsKeywordsPredicate(Collections.singletonList("Mid"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Multiple keywords
        predicate = new PriorityContainsKeywordsPredicate(Arrays.asList("Mid", "Low"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Low").build()));

        // Partial keyword
        predicate = new PriorityContainsKeywordsPredicate(Arrays.asList("Mi"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Partial mixed-case keyword
        predicate = new PriorityContainsKeywordsPredicate(Arrays.asList("mI"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Mixed-case keywords
        predicate = new PriorityContainsKeywordsPredicate(Arrays.asList("lOw"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Low").build()));
    }

    @Test
    public void test_priorityDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PriorityContainsKeywordsPredicate predicate = new PriorityContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Non-matching keyword
        predicate = new PriorityContainsKeywordsPredicate(Arrays.asList("Mid"));
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("Low").build()));

        // Keywords match question, answer, category and tag, but does not match priority
        predicate = new PriorityContainsKeywordsPredicate(Arrays.asList("Test", "Random", "High",
                "Theorem", "Equation"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Theorem").withAnswer("Test")
                .withCategory("Random").withPriority("Mid").withTags("Equation").build()));
    }
}
