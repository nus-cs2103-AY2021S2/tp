package seedu.cakecollate.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.logic.parser.Prefix;
import seedu.cakecollate.testutil.OrderBuilder;

public class ContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        HashMap<Prefix, List<String>> map1 = new HashMap<>();
        map1.put(PREFIX_NAME, firstPredicateKeywordList);
        HashMap<Prefix, List<String>> map2 = new HashMap<>();
        map1.put(PREFIX_NAME, secondPredicateKeywordList);

        ContainsKeywordsPredicate firstPredicate = new ContainsKeywordsPredicate(map1);
        ContainsKeywordsPredicate secondPredicate = new ContainsKeywordsPredicate(map2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsKeywordsPredicate firstPredicateCopy = new ContainsKeywordsPredicate(map1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different order -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsKeywords_returnsTrue() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // One keyword
        map.clear();
        map.put(PREFIX_NAME, Collections.singletonList("Alice"));
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("Alice", "Bob"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("Bob", "Carol"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("aLIce", "bOB"));
        predicate = new ContainsKeywordsPredicate(map);
        assertTrue(predicate.test(new OrderBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_doesNotContainKeywords_returnsFalse() {
        HashMap<Prefix, List<String>> map = new HashMap<>();

        // Zero keywords
        map.clear();
        map.put(PREFIX_NAME, Collections.emptyList());
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(map);
        assertFalse(predicate.test(new OrderBuilder().withName("Alice").build()));

        // Non-matching keyword
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("Carol"));
        predicate = new ContainsKeywordsPredicate(map);
        assertFalse(predicate.test(new OrderBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        map.clear();
        map.put(PREFIX_NAME, Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        predicate = new ContainsKeywordsPredicate(map);
        assertFalse(predicate.test(new OrderBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
