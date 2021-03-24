package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class DataContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        DataContainsKeywordsPredicate firstPredicate = new
                DataContainsKeywordsPredicate(firstPredicateKeywordList);
        DataContainsKeywordsPredicate secondPredicate = new
                DataContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DataContainsKeywordsPredicate firstPredicateCopy = new
                DataContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different endpoint -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_dataContainsKeywords_returnsTrue() {
        // One keyword
        DataContainsKeywordsPredicate predicate = new
            DataContainsKeywordsPredicate(Collections.singletonList("key"));
        assertTrue(predicate.test(new EndpointBuilder().withData("{\"key\":\"value\"}").build()));

        // Only one matching keyword
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("get", "{\"key\":\"value\"}"));
        assertTrue(predicate.test(new EndpointBuilder().withData("{\"key\":\"value\"}").build()));

        // Mixed-case keywords
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("keY"));
        assertTrue(predicate.test(new EndpointBuilder().withData("{\"key\":\"value\"}").build()));

        // Value match
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("value"));
        assertTrue(predicate.test(new EndpointBuilder().withData("{\"key\":\"value\"}").build()));

    }

    @Test
    public void test_dataDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DataContainsKeywordsPredicate predicate = new
                DataContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EndpointBuilder().withData("{\"key\":\"value\"}").build()));

        // Non-matching keyword
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("POST"));
        assertFalse(predicate.test(new EndpointBuilder().withData("{\"key\":\"value\"}").build()));

        // Keywords match address but does not match data
        predicate = new DataContainsKeywordsPredicate(Arrays.asList("google.com"));
        assertFalse(predicate.test(new
                EndpointBuilder().withData("{\"key\":\"value\"}").withAddress("google.com").build()));
    }
}

