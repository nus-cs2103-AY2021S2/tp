package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EntryBuilder;

public class EntryTagsContainKeywordsPredicateTest {

    @Test
    public void test_entryTagIsEmpty_returnFalse() {
        List<String> keywords = Arrays.asList(EntryBuilder.DEFAULT_TAG);
        EntryTagsContainKeywordsPredicate predicate = new EntryTagsContainKeywordsPredicate(keywords);
        assertFalse(predicate.test(new EntryBuilder().withTags().build()));
    }

    @Test
    public void test_entryTagsContainsAllKeywords_returnTrue() {
        List<String> keywords = Arrays.asList("ALl", "Key", "Words");
        EntryTagsContainKeywordsPredicate predicate = new EntryTagsContainKeywordsPredicate(keywords);
        assertTrue(predicate.test(new EntryBuilder().withTags("All", "Key", "Words").build()));
    }

    @Test
    public void test_entryTagsDoesNotFullyMatchAllKeywords_returnFalse() {
        List<String> keywords = Arrays.asList("Not", "All");
        EntryTagsContainKeywordsPredicate predicate = new EntryTagsContainKeywordsPredicate(keywords);
        assertFalse(predicate.test(new EntryBuilder().withTags("All").build()));
    }

    @Test
    public void test_entryTagsContainsKeywordsAndMore_returnTrue() {
        List<String> keywords = Arrays.asList("All", "More");
        EntryTagsContainKeywordsPredicate predicate = new EntryTagsContainKeywordsPredicate(keywords);
        assertTrue(predicate.test(new EntryBuilder().withTags("All", "And", "More").build()));
    }

    @Test
    public void test_entryTagMatchesKeywordPartially_returnFalse() {
        List<String> keywords = Arrays.asList("Match");
        EntryTagsContainKeywordsPredicate predicate = new EntryTagsContainKeywordsPredicate(keywords);
        assertFalse(predicate.test(new EntryBuilder().withTags("Mat").build()));
    }

    @Test
    public void test_entryTagKeywordCasingMismatch_returnTrue() {
        List<String> keywords = Arrays.asList("MiSmaTCh");
        EntryTagsContainKeywordsPredicate predicate = new EntryTagsContainKeywordsPredicate(keywords);
        assertTrue(predicate.test(new EntryBuilder().withTags("mismatch").build()));
    }

    @Test
    public void equals() {
        List<String> keywords = Arrays.asList("Test", "equals");
        EntryTagsContainKeywordsPredicate testPredicate = new EntryTagsContainKeywordsPredicate(keywords);

        // same object -> return true
        assertTrue(testPredicate.equals(testPredicate));

        // null -> return false
        assertFalse(testPredicate.equals(null));

        // different object, same keywords -> return true
        assertTrue(testPredicate.equals(new EntryTagsContainKeywordsPredicate(keywords)));

        // different object, different keywords -> return false
        assertFalse(testPredicate.equals(new EntryTagsContainKeywordsPredicate(Arrays.asList("lol"))));
    }
}
