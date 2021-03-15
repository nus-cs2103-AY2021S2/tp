package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different object -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));


        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Carol").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));

        // Non-matching partial keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Ali"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));

        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Bo"));
        assertFalse(predicate.test(new FlashcardBuilder().withTags("Alice", "Bob").build()));

        // Keywords match question, answer, category and priority, but does not match tags
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Theorem", "Test", "Mid", "Random",
                "Equation", "Common"));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Theorem").withAnswer("Test")
                .withCategory("Random").withPriority("Mid").withTags("Formula").build()));
        assertFalse(predicate.test(new FlashcardBuilder().withQuestion("Theorem").withAnswer("Test")
                .withCategory("Random").withPriority("Mid").withTags("Formula", "Famous").build()));
    }
}
