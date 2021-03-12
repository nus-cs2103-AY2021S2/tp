package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class CategoryContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        QuestionContainsKeywordsPredicate firstPredicate =
                new QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
        QuestionContainsKeywordsPredicate secondPredicate =
                new QuestionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        QuestionContainsKeywordsPredicate firstPredicateCopy =
                new QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different flashcard -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        CategoryContainsKeywordsPredicate predicate =
                new CategoryContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));

        // Multiple keywords
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));

        // Only one matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CategoryContainsKeywordsPredicate predicate = new CategoryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice").build()));

        // Non-matching keyword
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new CategoryContainsKeywordsPredicate(Arrays.asList("Theorem", "Test", "Mid", "Math"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Theorem").withAnswer("Test")
                .withCategory("Random").withPriority("Mid").build()));
    }
}
