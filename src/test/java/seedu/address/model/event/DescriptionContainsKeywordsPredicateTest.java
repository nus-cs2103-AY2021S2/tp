package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Description;

public class DescriptionContainsKeywordsPredicateTest {
    private final List<String> keywords1 = Collections.singletonList("EVENT1");
    private final List<String> keywords2 = Arrays.asList("EVENT1", "EVENT2");
    private final Description description1 = new Description("EVENT1");
    private final Description description2 = new Description("EVENT2");
    private final Description description3 = new Description("EVENT3 EVENT4");
    private final LocalDateTime date = LocalDateTime.of(2021, 3, 4, 23, 59);

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(keywords1);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(keywords2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same attributes -> returns true
        DescriptionContainsKeywordsPredicate secondPredicateCopy =
                new DescriptionContainsKeywordsPredicate(keywords2);
        assertTrue(secondPredicateCopy.equals(secondPredicate));

        // different instance -> returns false
        assertFalse(secondPredicate.equals(2));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different attributes -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GeneralEvent(description1, date)));

        // Non-matching keywords
        predicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("EVENT3"));
        assertFalse(predicate.test(new GeneralEvent(description1, date)));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Arrays.asList("EVENT2"));
        assertTrue(predicate.test(new GeneralEvent(description2, date)));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("EVENT3", "EVENT3"));
        assertTrue(predicate.test(new GeneralEvent(description3, date)));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("EVENT3"));
        assertTrue(predicate.test(new GeneralEvent(description3, date)));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("eVenT3", "EvenT3"));
        assertTrue(predicate.test(new GeneralEvent(description3, date)));
    }
}
