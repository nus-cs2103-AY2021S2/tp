package seedu.address.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.EventNameContainsKeywordsPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TaskBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskNameContainsKeywordsPredicate firstTaskPredicate =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskNameContainsKeywordsPredicate secondTaskPredicate =
                new TaskNameContainsKeywordsPredicate(secondPredicateKeywordList);
        EventNameContainsKeywordsPredicate firstEventPredicate =
                new EventNameContainsKeywordsPredicate(firstPredicateKeywordList);
        EventNameContainsKeywordsPredicate secondEventPredicate =
                new EventNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // ------Task--------

        // same object -> returns true
        assertTrue(firstTaskPredicate.equals(firstTaskPredicate));

        // same values -> returns true
        TaskNameContainsKeywordsPredicate firstTaskPredicateCopy =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstTaskPredicate.equals(firstTaskPredicateCopy));

        // different types -> returns false
        assertFalse(firstTaskPredicate.equals(1));

        // null -> returns false
        assertFalse(firstTaskPredicate.equals(null));

        // different tasks -> returns false
        assertFalse(firstTaskPredicate.equals(secondTaskPredicate));

        // ------Event--------

        // same object -> returns true
        assertTrue(firstEventPredicate.equals(firstEventPredicate));

        // same values -> returns true
        EventNameContainsKeywordsPredicate firstEventPredicateCopy =
                new EventNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstEventPredicate.equals(firstEventPredicateCopy));

        // different types -> returns false
        assertFalse(firstEventPredicate.equals(1));

        // null -> returns false
        assertFalse(firstEventPredicate.equals(null));

        // different events -> returns false
        assertFalse(firstEventPredicate.equals(secondEventPredicate));
    }

    @Test
    public void test_taskNameContainsKeywords_returnsTrue() {
        // One keyword
        TaskNameContainsKeywordsPredicate taskPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("Assignment"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("CS2105 Assignment").build()));

        // Multiple keywords
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("CS2105", "Assignment"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("CS2105 Assignment").build()));

        // Only one matching keyword
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("CS2105", "Assignment"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("CS2105 Lab").build()));

        // Mixed-case keywords
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("cs2105", "AsSiGnmEnt"));
        assertTrue(taskPredicate.test(new TaskBuilder().withName("CS2105 Assignment").build()));
    }

    @Test
    public void test_eventNameContainsKeywords_returnsTrue() {
        // One keyword
        EventNameContainsKeywordsPredicate eventPredicate =
                new EventNameContainsKeywordsPredicate(Collections.singletonList("Assignment"));
        assertTrue(eventPredicate.test(new EventBuilder().withName("CS2105 Assignment").build()));

        // Multiple keywords
        eventPredicate = new EventNameContainsKeywordsPredicate(Arrays.asList("CS2105", "Assignment"));
        assertTrue(eventPredicate.test(new EventBuilder().withName("CS2105 Assignment").build()));

        // Only one matching keyword
        eventPredicate = new EventNameContainsKeywordsPredicate(Arrays.asList("CS2105", "Assignment"));
        assertTrue(eventPredicate.test(new EventBuilder().withName("CS2105 Lab").build()));

        // Mixed-case keywords
        eventPredicate = new EventNameContainsKeywordsPredicate(Arrays.asList("cs2105", "AsSiGnmEnt"));
        assertTrue(eventPredicate.test(new EventBuilder().withName("CS2105 Assignment").build()));
    }

    @Test
    public void test_taskNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskNameContainsKeywordsPredicate taskPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(taskPredicate.test(new TaskBuilder().withName("Assignment").build()));

        // Non-matching keyword
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Lab"));
        assertFalse(taskPredicate.test(new TaskBuilder().withName("CS2105 Assignment").build()));

        // Keywords match deadline, category and priority, but does not match name
        taskPredicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("2020-01-01", "schoolwork", "6"));
        assertFalse(taskPredicate.test(new TaskBuilder().withName("CS2105 Assignment")
                .withDeadline("2020-01-01")
                .withCategories("schoolwork").withPriority("6").build()));
    }

    @Test
    public void test_eventNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EventNameContainsKeywordsPredicate eventPredicate =
                new EventNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(eventPredicate.test(new EventBuilder().withName("Assignment").build()));

        // Non-matching keyword
        eventPredicate = new EventNameContainsKeywordsPredicate(Arrays.asList("Lab"));
        assertFalse(eventPredicate.test(new EventBuilder().withName("CS2105 Assignment").build()));

        // Keywords match startdate, category and priority, but does not match name
        eventPredicate = new EventNameContainsKeywordsPredicate(Arrays.asList("2020-01-01", "schoolwork", "difficult"));
        assertFalse(eventPredicate.test(new EventBuilder().withName("CS2105 Assignment")
                .withStartDate("2020-01-01")
                .withCategories("schoolwork").withTags("difficult").build()));
    }
}
