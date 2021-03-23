package seedu.smartlib.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartlib.testutil.BookBuilder;

public class BookNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        BookNameContainsKeywordsPredicate firstPredicate =
                new BookNameContainsKeywordsPredicate(firstPredicateKeywordList);
        BookNameContainsKeywordsPredicate secondPredicate =
                new BookNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookNameContainsKeywordsPredicate firstPredicateCopy =
                new BookNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different book -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        BookNameContainsKeywordsPredicate predicate =
                new BookNameContainsKeywordsPredicate(Collections.singletonList("Harry"));
        assertTrue(predicate.test(new BookBuilder().withName("Harry Potter").build()));

        // Multiple keywords
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("Harry", "Potter"));
        assertTrue(predicate.test(new BookBuilder().withName("Harry Potter").build()));

        // Only one matching keyword
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("Maze", "Hobbit"));
        assertTrue(predicate.test(new BookBuilder().withName("Harry Hobbit").build()));

        // Mixed-case keywords
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("HaRrY", "pOtTeR"));
        assertTrue(predicate.test(new BookBuilder().withName("Harry Potter").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BookNameContainsKeywordsPredicate predicate = new BookNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new BookBuilder().withName("Harry").build()));

        // Non-matching keyword
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("Hobbit"));
        assertFalse(predicate.test(new BookBuilder().withName("Harry Potter").build()));

        // Keywords match author, publisher, isbn and genre, but does not match name
        predicate = new BookNameContainsKeywordsPredicate(
                Arrays.asList("Rowling", "Bloomsbury", "1234567890123", "Fantasy"));
        assertFalse(predicate.test(new BookBuilder().withName("Harry").withAuthor("Rowling")
                .withPublisher("Bloomsbury").withIsbn("1234567890123").withGenre("Fantasy").build()));

    }


}
