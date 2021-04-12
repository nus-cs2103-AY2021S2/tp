package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.us.among.testutil.EndpointBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        TagsContainsKeywordsPredicate firstPredicate = new
                TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new
                TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new
                TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different endpoint -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate = new
            TagsContainsKeywordsPredicate(Collections.singletonList("cat"));
        assertTrue(predicate.test(new EndpointBuilder().withTags("cat").build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("cat", "PATCH"));
        assertTrue(predicate.test(new EndpointBuilder().withTags("cat").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("CAt", "PoSt"));
        assertTrue(predicate.test(new EndpointBuilder().withTags("cat").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new
                TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new EndpointBuilder().withTags("cat").build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("POST"));
        assertFalse(predicate.test(new EndpointBuilder().withTags("cat").build()));

        // Non-matching keyword (partially matched)
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("catter"));
        assertFalse(predicate.test(new EndpointBuilder().withTags("cat").build()));

        // Non-matching keyword + other field
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("key"));
        assertFalse(predicate.test(new EndpointBuilder().withTags("cat").withHeaders("\"key\":\"value\"").build()));

    }
}

