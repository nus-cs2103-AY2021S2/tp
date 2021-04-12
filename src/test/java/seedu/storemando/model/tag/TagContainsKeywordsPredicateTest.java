package seedu.storemando.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.tag.predicate.TagContainsKeywordsPredicate;
import seedu.storemando.testutil.ItemBuilder;

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

        // different item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsFalse() {
        TagContainsKeywordsPredicate predicate =
            new TagContainsKeywordsPredicate(Collections.singletonList("Expiring"));
        assertFalse(predicate.test(new ItemBuilder().withTags("notExpiring").build())); // no match
        assertFalse(predicate.test(new ItemBuilder().withTags("favourite").build())); // all small case and no match
        assertFalse(predicate.test(new ItemBuilder().withTags("eexpiring").build())); // different spelling
        assertFalse(predicate.test(new ItemBuilder().withTags("expir").build())); // partial tag match
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        TagContainsKeywordsPredicate predicate =
            new TagContainsKeywordsPredicate(Collections.singletonList("Favourite"));
        assertTrue(predicate.test(new ItemBuilder().withTags("Favourite").build())); // exact match
        assertTrue(predicate.test(new ItemBuilder().withTags("favourite").build())); // all small case
        assertTrue(predicate.test(new ItemBuilder().withTags("FAVOURITE").build())); // all upper case
        assertTrue(predicate.test(new ItemBuilder().withTags("faVourIte").build())); // mix of upper and lower case
    }
}
