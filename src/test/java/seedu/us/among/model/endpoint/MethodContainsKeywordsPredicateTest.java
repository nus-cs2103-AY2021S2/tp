package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class MethodContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        MethodContainsKeywordsPredicate firstPredicate = new
                MethodContainsKeywordsPredicate(firstPredicateKeywordList);
        MethodContainsKeywordsPredicate secondPredicate = new
                MethodContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MethodContainsKeywordsPredicate firstPredicateCopy = new
                MethodContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different endpoint -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_methodContainsKeywords_returnsTrue() {
        // One keyword
        MethodContainsKeywordsPredicate predicate = new
            MethodContainsKeywordsPredicate(Collections.singletonList("GET"));
        assertTrue(predicate.test(new EndpointBuilder().withMethod("GET").build()));


        // Only one matching keyword
        predicate = new MethodContainsKeywordsPredicate(Arrays.asList("POST", "PATCH"));
        assertTrue(predicate.test(new EndpointBuilder().withMethod("PATCH").build()));

        // Mixed-case keywords
        predicate = new MethodContainsKeywordsPredicate(Arrays.asList("gEt", "PoSt"));
        assertTrue(predicate.test(new EndpointBuilder().withMethod("POST").build()));
    }

    @Test
    public void test_methodDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MethodContainsKeywordsPredicate predicate = new
                MethodContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EndpointBuilder().withMethod("GET").build()));


        // Non-matching keyword
        predicate = new MethodContainsKeywordsPredicate(Arrays.asList("POST"));
        assertFalse(predicate.test(new EndpointBuilder().withMethod("GET").build()));

        // Keywords match address but does not match method
        predicate = new MethodContainsKeywordsPredicate(Arrays.asList("get", "google.com"));
        assertFalse(predicate.test(new EndpointBuilder().withMethod("post").withAddress("google.com").build()));
    }
}

