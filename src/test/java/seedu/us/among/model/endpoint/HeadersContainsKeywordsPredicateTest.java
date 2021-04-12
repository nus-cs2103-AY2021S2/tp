package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class HeadersContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        HeadersContainsKeywordsPredicate firstPredicate = new
                HeadersContainsKeywordsPredicate(firstPredicateKeywordList);
        HeadersContainsKeywordsPredicate secondPredicate = new
                HeadersContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        HeadersContainsKeywordsPredicate firstPredicateCopy = new
                HeadersContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different endpoint -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_headerContainsKeywords_returnsTrue() {
        // One keyword
        HeadersContainsKeywordsPredicate predicate = new
            HeadersContainsKeywordsPredicate(Collections.singletonList("key"));
        assertTrue(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").build()));

        // Only one matching keyword
        predicate = new HeadersContainsKeywordsPredicate(Arrays.asList("cat", "key"));
        assertTrue(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").build()));

        // Mixed-case keywords
        predicate = new HeadersContainsKeywordsPredicate(Arrays.asList("keY"));
        assertTrue(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").build()));
    }

    @Test
    public void test_headerDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        HeadersContainsKeywordsPredicate predicate = new
                HeadersContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").build()));

        // Non-matching keyword
        predicate = new HeadersContainsKeywordsPredicate(Arrays.asList("POST"));
        assertFalse(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").build()));

        // Non-matching keyword (partially matched)
        predicate = new HeadersContainsKeywordsPredicate(Arrays.asList("keyyer"));
        assertFalse(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").build()));

        // Non-matching keyword + other field
        predicate = new HeadersContainsKeywordsPredicate(Arrays.asList("get"));
        assertFalse(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").withMethod("get").build()));

    }
}

