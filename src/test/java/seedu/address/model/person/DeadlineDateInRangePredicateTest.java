package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class DeadlineDateInRangePredicateTest {
    private static final DateTimeFormatter dateDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void equals() {
        Optional<DeadlineDate> emptyPredicateDeadline = Optional.empty(); //empty
        Optional<DeadlineDate> firstPredicateDeadline = Optional.of(new DeadlineDate("10-11-2022"));
        Optional<DeadlineDate> secondPredicateDeadline = Optional.of(new DeadlineDate("10-11-2042"));


        DeadlineDateInRangePredicate firstPredicate = new DeadlineDateInRangePredicate(
                emptyPredicateDeadline, emptyPredicateDeadline); //both empty
        DeadlineDateInRangePredicate secondPredicate = new DeadlineDateInRangePredicate(
                firstPredicateDeadline, secondPredicateDeadline); //both filled
        DeadlineDateInRangePredicate thirdPredicate = new DeadlineDateInRangePredicate(
                emptyPredicateDeadline, secondPredicateDeadline); //start empty
        DeadlineDateInRangePredicate fourthPredicate = new DeadlineDateInRangePredicate(
                firstPredicateDeadline, emptyPredicateDeadline); // end empty

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(secondPredicate.equals(secondPredicate));
        assertTrue(thirdPredicate.equals(thirdPredicate));
        assertTrue(fourthPredicate.equals(fourthPredicate));

        // same values -> returns true
        DeadlineDateInRangePredicate firstPredicateCopy = new DeadlineDateInRangePredicate(
                emptyPredicateDeadline, emptyPredicateDeadline);
        DeadlineDateInRangePredicate secondPredicateCopy = new DeadlineDateInRangePredicate(
                firstPredicateDeadline, secondPredicateDeadline);
        DeadlineDateInRangePredicate thirdPredicateCopy = new DeadlineDateInRangePredicate(
                emptyPredicateDeadline, secondPredicateDeadline);
        DeadlineDateInRangePredicate fourthPredicateCopy = new DeadlineDateInRangePredicate(
                firstPredicateDeadline, emptyPredicateDeadline);
        assertTrue(firstPredicate.equals(firstPredicateCopy));
        assertTrue(secondPredicate.equals(secondPredicateCopy));
        assertTrue(thirdPredicate.equals(thirdPredicateCopy));
        assertTrue(fourthPredicate.equals(fourthPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1)); // integer
        assertFalse(secondPredicate.equals(1));
        assertFalse(thirdPredicate.equals(1));
        assertFalse(fourthPredicate.equals(1));
        assertFalse(firstPredicate.equals("rand")); //string
        assertFalse(secondPredicate.equals("rand"));
        assertFalse(thirdPredicate.equals("rand"));
        assertFalse(fourthPredicate.equals("rand"));
        assertFalse(firstPredicate.equals(new Object())); // object
        assertFalse(secondPredicate.equals(new Object()));
        assertFalse(thirdPredicate.equals(new Object()));
        assertFalse(fourthPredicate.equals(new Object()));
        assertFalse(firstPredicate.equals(new DeadlineTime("10:10"))); // DeadlineTime
        assertFalse(secondPredicate.equals(new DeadlineTime("10:10")));
        assertFalse(thirdPredicate.equals(new DeadlineTime("10:10")));
        assertFalse(fourthPredicate.equals(new DeadlineTime("10:10")));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
        assertFalse(secondPredicate.equals(null));
        assertFalse(thirdPredicate.equals(null));
        assertFalse(fourthPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
        assertFalse(firstPredicate.equals(thirdPredicate));
        assertFalse(firstPredicate.equals(fourthPredicate));
        assertFalse(secondPredicate.equals(thirdPredicate));
        assertFalse(secondPredicate.equals(fourthPredicate));
        assertFalse(thirdPredicate.equals(fourthPredicate));
    }

    @Test
    public void test_deadlineDateInRange_returnsTrue() {
        Optional<DeadlineDate> emptyPredicateDeadline = Optional.empty(); //empty
        Optional<DeadlineDate> firstPredicateDeadline = Optional.of(new DeadlineDate("10-11-2022"));
        Optional<DeadlineDate> secondPredicateDeadline = Optional.of(new DeadlineDate("10-11-2042"));
        String eightDaysFromToday = LocalDate.now().plusDays(8).format(dateDateFormatter);

        // No deadline given
        DeadlineDateInRangePredicate predicate = new DeadlineDateInRangePredicate(
                emptyPredicateDeadline, emptyPredicateDeadline);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(eightDaysFromToday).build()));

        // Start date given
        predicate = new DeadlineDateInRangePredicate(firstPredicateDeadline, emptyPredicateDeadline);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("10-11-2022").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("11-11-2022").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("01-12-2022").build()));

        // End date given
        predicate = new DeadlineDateInRangePredicate(emptyPredicateDeadline, secondPredicateDeadline);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("10-11-2042").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("09-11-2042").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("01-01-2042").build()));

        // Both start date and end date given
        predicate = new DeadlineDateInRangePredicate(firstPredicateDeadline, secondPredicateDeadline);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("10-11-2022").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("11-11-2022").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("01-12-2022").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("10-11-2042").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("09-11-2042").build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate("01-01-2042").build()));
    }

    @Test
    public void test_deadlineDateInRange_returnsFalse() {
        Optional<DeadlineDate> emptyPredicateDeadline = Optional.empty(); //empty
        Optional<DeadlineDate> firstPredicateDeadline = Optional.of(new DeadlineDate("10-11-2022"));
        Optional<DeadlineDate> secondPredicateDeadline = Optional.of(new DeadlineDate("10-11-2042"));
        String today = LocalDate.now().format(dateDateFormatter);
        String eightDaysFromToday = LocalDate.now().plusDays(8).format(dateDateFormatter);

        // No deadline given - Today's date will not included
        DeadlineDateInRangePredicate predicate = new DeadlineDateInRangePredicate(
                emptyPredicateDeadline, emptyPredicateDeadline);
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(today).build()));

        // Start date given - Date given is below it
        predicate = new DeadlineDateInRangePredicate(firstPredicateDeadline, emptyPredicateDeadline);
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("09-11-2022").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("08-11-2022").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("01-01-2020").build()));

        // End date given - Date given after it
        predicate = new DeadlineDateInRangePredicate(emptyPredicateDeadline, secondPredicateDeadline);
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("11-11-2042").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("12-11-2042").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("12-12-2099").build()));

        // Both start date and end date given - Date given before the start and after the end
        predicate = new DeadlineDateInRangePredicate(firstPredicateDeadline, secondPredicateDeadline);
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("09-11-2022").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("08-11-2022").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("01-01-2020").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("11-11-2042").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("12-11-2042").build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate("12-12-2099").build()));
    }
}
