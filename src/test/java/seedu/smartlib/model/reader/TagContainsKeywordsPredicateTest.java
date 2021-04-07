package seedu.smartlib.model.reader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartlib.testutil.ReaderBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(" "));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        assertTrue(predicate.test(new ReaderBuilder().withTags("first").build()));
        assertTrue(predicate.test(new ReaderBuilder().withTags("third", "second", "first").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("first", "second"));
        assertTrue(predicate.test(new ReaderBuilder().withTags("second", "first").build()));
        assertTrue(predicate.test(new ReaderBuilder().withTags("third", "second", "first").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("first", "second"));
        assertTrue(predicate.test(new ReaderBuilder().withTags("first", "third").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("fIrSt", "tHIRd"));
        assertTrue(predicate.test(new ReaderBuilder().withTags("third", "second", "first").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ReaderBuilder().withTags("third", "second", "first").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("fourth"));
        assertFalse(predicate.test(new ReaderBuilder().withTags("third", "second", "first").build()));

        // Keywords match phone, email and address, but does not match tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(
                predicate.test(
                        new ReaderBuilder()
                                .withTags("third", "second", "first")
                                .withPhone("12345")
                                .withEmail("alice@email.com")
                                .withAddress("Main Street")
                                .build()
                )
        );
    }

}
