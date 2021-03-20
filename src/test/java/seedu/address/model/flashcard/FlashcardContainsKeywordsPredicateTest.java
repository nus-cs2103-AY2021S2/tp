package seedu.address.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class FlashcardContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FlashcardContainsKeywordsPredicate firstPredicate =
                new FlashcardContainsKeywordsPredicate(firstPredicateKeywordList);
        FlashcardContainsKeywordsPredicate secondPredicate =
                new FlashcardContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FlashcardContainsKeywordsPredicate firstPredicateCopy =
                new FlashcardContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different object -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_flashcardContainsKeywords_returnsTrue() {
        // One keyword
        FlashcardContainsKeywordsPredicate predicate =
                new FlashcardContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
        predicate = new FlashcardContainsKeywordsPredicate(Collections.singletonList("Mid"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // One partial keyword
        predicate = new FlashcardContainsKeywordsPredicate(Collections.singletonList("Ali"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
        predicate = new FlashcardContainsKeywordsPredicate(Collections.singletonList("Mi"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Multiple keywords
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Mid", "High"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Multiple partial keywords
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Ali", "Bo"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("mi", "Hi"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Only one matching keyword
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Carol").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Carol").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Carol").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alic", "Carol").build()));

        // Only one matching partial keyword
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Bo", "Car"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Carol").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Carol").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Carol").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alic", "Carol").build()));

        // Mixed-case keywords
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
        predicate = new FlashcardContainsKeywordsPredicate(Collections.singletonList("mId"));
        assertTrue(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Mixed-case partial keywords
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("aLI", "Ob"));
        assertTrue(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
    }

    @Test
    public void test_flashcardDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FlashcardContainsKeywordsPredicate predicate = new FlashcardContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Alice").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withAnswer("Alice").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Alice").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Non-matching keyword
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Non-matching bigger keyword
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Alicea"));
        assertFalse(predicate.test(new FlashcardBuilder().withCategory("Alice Bob").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Alice Bob").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withAnswer("Alice Bob").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("Middle"));
        assertFalse(predicate.test(new FlashcardBuilder().withPriority("Mid").build()));

        // Keywords match no flashcard fields
        predicate = new FlashcardContainsKeywordsPredicate(Arrays.asList("testing", "Tested", "123", "Middle", "High"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Theorem").withAnswer("Test")
                .withCategory("Random").withPriority("Mid").withTags("Equation").build()));
    }
}
