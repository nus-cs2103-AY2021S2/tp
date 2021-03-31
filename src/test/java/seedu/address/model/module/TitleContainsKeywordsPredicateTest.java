package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TitleContainsKeywordsPredicateTest {
    private final List<String> keywords1 = Collections.singletonList("MOD1");
    private final List<String> keywords2 = Arrays.asList("MOD2", "MOD1");
    private final Title title1 = new Title("MOD1");
    private final Title title2 = new Title("MOD2");
    private final Title title3 = new Title("MOD3 MOD4");

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate predicate1 = new TitleContainsKeywordsPredicate(keywords1);
        TitleContainsKeywordsPredicate predicate2 = new TitleContainsKeywordsPredicate(keywords2);

        // same object -> returns true
        assertTrue(predicate1.equals(predicate1));

        // same attributes -> returns true
        TitleContainsKeywordsPredicate copyPredicate2 =
                new TitleContainsKeywordsPredicate(keywords2);
        assertTrue(predicate2.equals(copyPredicate2));

        // different instances -> return false
        assertFalse(predicate1.equals(2));

        // null -> returns false
        assertFalse(predicate2.equals(null));

        // different attributes -> return false
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new Module(title1)));

        // Non-matching keywords
        predicate = new TitleContainsKeywordsPredicate(Collections.singletonList("MOD3"));
        assertFalse(predicate.test(new Module(title2)));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Arrays.asList("MOD2"));
        assertTrue(predicate.test(new Module(title2)));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("MOD3", "MOD3"));
        assertTrue(predicate.test(new Module(title3)));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("MOD3"));
        assertTrue(predicate.test(new Module(title3)));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("mOd3", "MoD3"));
        assertTrue(predicate.test(new Module(title3)));
    }
}
