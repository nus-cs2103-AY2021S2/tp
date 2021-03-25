package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class AddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        AddressContainsKeywordsPredicate firstPredicate = new
                AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        AddressContainsKeywordsPredicate secondPredicate = new
                AddressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AddressContainsKeywordsPredicate firstPredicateCopy = new
                AddressContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different endpoint -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        AddressContainsKeywordsPredicate predicate = new
            AddressContainsKeywordsPredicate(Arrays.asList("google"));
        assertTrue(predicate.test(new EndpointBuilder().withAddress("https://google.com").build()));

        // Only one matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("google", "paper"));
        assertTrue(predicate.test(new EndpointBuilder().withAddress("https://google.com").build()));

        // Mixed-case keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("gOOglE"));
        assertTrue(predicate.test(new EndpointBuilder().withAddress("https://google.com").build()));

        // Partial keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("gOO"));
        assertTrue(predicate.test(new EndpointBuilder().withAddress("https://google.com").build()));

    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AddressContainsKeywordsPredicate predicate = new
                AddressContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EndpointBuilder().withAddress("https://google.com").build()));

        // Non-matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("POST"));
        assertFalse(predicate.test(new EndpointBuilder().withAddress("https://google.com").build()));

        // Keywords match method but does not match address
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("post", "pos"));
        assertFalse(predicate.test(new EndpointBuilder().withAddress("post").withAddress("google.com").build()));
    }
}

