package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.entry.EntryDate.DEFAULT_FORMATTER;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EntryBuilder;

public class ListEntryFormatPredicateTest {

    @Test void test_emptyPredicate_returnTrue() {
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("");
        assertTrue(predicate.test(new EntryBuilder().build()));
    }

    @Test
    public void test_dayPredicateEntryStartToday_returnTrue() {
        String testStartDate = LocalDateTime.now().format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().plusMinutes(1).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("day");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertTrue(predicate.test(todayEntry));
    }

    @Test
    public void test_dayPredicateEntryStartAfter_returnFalse() {
        String testStartDate = LocalDateTime.now().plusDays(1).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().plusDays(2).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("day");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertFalse(predicate.test(todayEntry));
    }

    @Test
    public void test_dayPredicateEntryEndBefore_returnFalse() {
        String testStartDate = LocalDateTime.now().minusDays(2).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().minusDays(1).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("day");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertFalse(predicate.test(todayEntry));
    }

    @Test
    public void test_dayPredicateEntryStartBeforeEndToday_returnTrue() {
        String testStartDate = LocalDateTime.now().minusDays(1).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("day");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertTrue(predicate.test(todayEntry));
    }

    @Test
    public void test_dayPredicateEntryStartBeforeEndAfter_returnTrue() {
        String testStartDate = LocalDateTime.now().minusDays(2).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().plusDays(2).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("day");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertTrue(predicate.test(todayEntry));
    }

    @Test
    public void test_weekPredicateEntryStartThisWeek_returnTrue() {
        String testStartDate = LocalDateTime.now().plusDays(6).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().plusDays(10).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("week");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertTrue(predicate.test(todayEntry));
    }

    @Test
    public void test_weekPredicateEntryEndThisWeek_returnTrue() {
        String testStartDate = LocalDateTime.now().minusDays(10).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().plusDays(6).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("week");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertTrue(predicate.test(todayEntry));

    }

    @Test
    public void test_weekPredicateEntryStartBeforeEndAfter_returnTrue() {
        String testStartDate = LocalDateTime.now().minusDays(10).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().plusDays(10).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("week");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertTrue(predicate.test(todayEntry));
    }

    @Test
    public void test_weekPredicateEntryStartAfterWeek_returnFalse() {
        String testStartDate = LocalDateTime.now().plusDays(10).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().plusDays(11).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("week");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertFalse(predicate.test(todayEntry));
    }

    @Test
    public void test_weekPredicateEntryEndBefore_returnFalse() {
        String testStartDate = LocalDateTime.now().minusDays(10).format(DEFAULT_FORMATTER);
        String testEndDate = LocalDateTime.now().minusDays(8).format(DEFAULT_FORMATTER);
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("week");

        Entry todayEntry = new EntryBuilder().withStartDate(testStartDate)
                .withEndDate(testEndDate).build();

        assertFalse(predicate.test(todayEntry));
    }

    @Test
    public void equals() {
        ListEntryFormatPredicate predicate = new ListEntryFormatPredicate("day");

        // same object -> return true
        assertTrue(predicate.equals(predicate));

        // same keyword -> return true
        assertTrue(predicate.equals(new ListEntryFormatPredicate("day")));

        // null -> return false
        assertFalse(predicate.equals(null));

        // different keyword -> return false
        assertFalse(predicate.equals(new ListEntryFormatPredicate("week")));
    }
}
