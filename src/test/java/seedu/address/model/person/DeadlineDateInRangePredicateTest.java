package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class DeadlineDateInRangePredicateTest {
    private static final DateTimeFormatter dateDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void equals() {
        long numberOfDays = 3;
        long numberOfWeeks = 3;
        DeadlineDateInRangePredicate firstPredicate = new DeadlineDateInRangePredicate(numberOfDays); //days
        DeadlineDateInRangePredicate secondPredicate = new DeadlineDateInRangePredicate(
                numberOfWeeks * 7); //weeks

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(secondPredicate.equals(secondPredicate));

        // same values -> returns true
        DeadlineDateInRangePredicate firstPredicateCopy = new DeadlineDateInRangePredicate(numberOfDays);
        DeadlineDateInRangePredicate secondPredicateCopy = new DeadlineDateInRangePredicate(
                numberOfWeeks * 7);
        assertTrue(firstPredicate.equals(firstPredicateCopy));
        assertTrue(secondPredicate.equals(secondPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1)); // integer
        assertFalse(secondPredicate.equals(1));
        assertFalse(firstPredicate.equals("rand")); //string
        assertFalse(secondPredicate.equals("rand"));
        assertFalse(firstPredicate.equals(new Object())); // object
        assertFalse(secondPredicate.equals(new Object()));
        assertFalse(firstPredicate.equals(new DeadlineTime("10:10"))); // DeadlineTime
        assertFalse(secondPredicate.equals(new DeadlineTime("10:10")));
        assertFalse(firstPredicate.equals(new DeadlineDate("10-10-2022"))); // DeadlineDate
        assertFalse(secondPredicate.equals(new DeadlineDate("10-11-2022")));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
        assertFalse(secondPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_deadlineDateInRange_returnsTrue() {
        long oneDayAfter = 1; // Lower bound
        long maxDaysAfter = ChronoUnit.DAYS.between(LocalDate.now().atStartOfDay(),
                LocalDate.of(2099, 12, 31).atStartOfDay()); //upper bound
        long oneWeekAfter = 1; // Lower bound
        long maxWeeksAfter = ChronoUnit.DAYS.between(LocalDate.now().atStartOfDay(),
                LocalDate.of(2099, 12, 31).atStartOfDay()) / 7; //upper bound
        String oneDayFromToday = LocalDate.now().plusDays(1)
                .format(dateDateFormatter); // 1 day from today
        String twoDaysFromToday = LocalDate.now().plusDays(2)
                .format(dateDateFormatter); // 2 days from today
        String sevenDaysFromToday = LocalDate.now().plusDays(7)
                .format(dateDateFormatter); // 7 days from today
        String eightDaysFromToday = LocalDate.now().plusDays(8)
                .format(dateDateFormatter); // 8 days from today
        String maxDays = LocalDate.now().plusDays(maxDaysAfter)
                .format(dateDateFormatter); // 8 days from today
        String maxWeeks = LocalDate.now().plusDays(maxWeeksAfter)
                .format(dateDateFormatter); // 8 days from today

        //Tomorrow
        DeadlineDateInRangePredicate predicate = new DeadlineDateInRangePredicate(oneDayAfter);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(oneDayFromToday).build()));

        //Within next week
        predicate = new DeadlineDateInRangePredicate(oneWeekAfter * 7);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(oneDayFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(twoDaysFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(sevenDaysFromToday).build()));

        //Until 31-12-2099 (by days)
        predicate = new DeadlineDateInRangePredicate(maxDaysAfter);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(oneDayFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(twoDaysFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(sevenDaysFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(eightDaysFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(maxDays).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(maxWeeks).build()));

        //Until 31-12-2099 (by weeks)
        predicate = new DeadlineDateInRangePredicate(maxWeeksAfter * 7);
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(oneDayFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(twoDaysFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(sevenDaysFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(eightDaysFromToday).build()));
        assertTrue(predicate.test(new TaskBuilder().withDeadlineDate(maxWeeks).build()));
    }

    @Test
    public void test_deadlineDateInRange_returnsFalse() {
        long oneDayAfter = 1; // Lower bound
        long maxDaysAfter = ChronoUnit.DAYS.between(LocalDate.now().atStartOfDay(),
                LocalDate.of(2099, 12, 31).atStartOfDay()); //upper bound
        long oneWeekAfter = 1; // Lower bound
        long maxWeeksAfter = ChronoUnit.DAYS.between(LocalDate.now().atStartOfDay(),
                LocalDate.of(2099, 12, 31).atStartOfDay()) / 7; //upper bound
        String twoDaysFromToday = LocalDate.now().plusDays(2)
                .format(dateDateFormatter); // 2 days from today
        String sevenDaysFromToday = LocalDate.now().plusDays(7)
                .format(dateDateFormatter); // 7 days from today
        String eightDaysFromToday = LocalDate.now().plusDays(8)
                .format(dateDateFormatter); // 8 days from today
        String maxDays = LocalDate.now().plusDays(maxDaysAfter)
                .format(dateDateFormatter); // 8 days from today
        String maxWeeks = LocalDate.now().plusDays(maxWeeksAfter)
                .format(dateDateFormatter); // 8 days from today

        //Tomorrow
        DeadlineDateInRangePredicate predicate = new DeadlineDateInRangePredicate(oneDayAfter);
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(twoDaysFromToday).build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(sevenDaysFromToday).build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(eightDaysFromToday).build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(maxDays).build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(maxWeeks).build()));

        //Within next week
        predicate = new DeadlineDateInRangePredicate(oneWeekAfter * 7);
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(eightDaysFromToday).build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(maxDays).build()));
        assertFalse(predicate.test(new TaskBuilder().withDeadlineDate(maxWeeks).build()));
    }
}
