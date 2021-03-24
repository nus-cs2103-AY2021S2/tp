package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class EndPointContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        EndPointContainsKeywordsPredicate firstPredicate = new
                EndPointContainsKeywordsPredicate(firstPredicateKeywordList);
        EndPointContainsKeywordsPredicate secondPredicate = new
                EndPointContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EndPointContainsKeywordsPredicate firstPredicateCopy = new
                EndPointContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different endpoint -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_endpointContainsKeywords_returnsTrue() {
        // One keyword
        EndPointContainsKeywordsPredicate predicate = new
            EndPointContainsKeywordsPredicate(Collections.singletonList("GET"));
        assertTrue(predicate.test(new EndpointBuilder().withMethod("GET").build()));

        // Only one matching keyword
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("POST", "PATCH"));
        assertTrue(predicate.test(new EndpointBuilder().withMethod("PATCH").build()));

        // Mixed-case keywords
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("gEt", "PoSt"));
        assertTrue(predicate.test(new EndpointBuilder().withMethod("POST").build()));

        // In Address field
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("google"));
        assertTrue(predicate.test(new EndpointBuilder().withAddress("https://google.com").build()));

        // In Data field
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("key"));
        assertTrue(predicate.test(new EndpointBuilder().withData("{\"key\":\"value\"}").build()));

        // In Tag field
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("cat"));
        assertTrue(predicate.test(new EndpointBuilder().withTags("cat").build()));

        // In Method field
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("get"));
        assertTrue(predicate.test(new EndpointBuilder().withMethod("get").build()));

        // In Header field
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("value"));
        assertTrue(predicate.test(new EndpointBuilder().withHeaders("\"key\":\"value\"").build()));

    }

    @Test
    public void test_endpointDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EndPointContainsKeywordsPredicate predicate = new
                EndPointContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EndpointBuilder().withMethod("GET").build()));


        // Non-matching keyword
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("POST"));
        assertFalse(predicate.test(new EndpointBuilder().withMethod("GET").build()));


        // Non-matching keyword (partially matched)
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("geter"));
        assertFalse(predicate.test(new EndpointBuilder().withMethod("GET").build()));


        // Non-matching keyword + other field
        predicate = new EndPointContainsKeywordsPredicate(Arrays.asList("abc"));
        assertFalse(predicate.test(new EndpointBuilder().withMethod("get").withHeaders("\"key\":\"value\"").build()));

    }
}

