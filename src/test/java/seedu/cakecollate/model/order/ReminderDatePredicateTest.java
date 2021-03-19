package seedu.cakecollate.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.testutil.OrderBuilder;

public class ReminderDatePredicateTest {

    @Test
    public void equals() {
        long zeroDays = 0L;
        long fiveDays = 5L;

        ReminderDatePredicate firstPredicate = new ReminderDatePredicate(zeroDays);
        ReminderDatePredicate secondPredicate = new ReminderDatePredicate(fiveDays);

        //same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> return true
        ReminderDatePredicate firstPredicateCopy = new ReminderDatePredicate(zeroDays);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        //different types -> return false
        assertFalse(firstPredicate.equals(1));

        //null-> returns false
        assertFalse(firstPredicate.equals(null));

        //different condition
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameDateWithin_returnsTrue() {

        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate testDate1 = LocalDate.now().plusDays(4L);
        // within 5 day from now, return true
        ReminderDatePredicate fiveDays = new ReminderDatePredicate(5L);
        assertTrue(fiveDays.test(new OrderBuilder()
                .withDeliveryDate(testDate1.format(format1)).build()));

        // within 4 days from now, return true
        ReminderDatePredicate zeroDays = new ReminderDatePredicate(4L);
        assertTrue(zeroDays.test(new OrderBuilder()
                .withDeliveryDate(testDate1.format(format1)).build()));
    }

    @Test
    public void test_nameDateNotWithin_returnsFalse() {

        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate testDate1 = LocalDate.now().plusDays(6L);
        LocalDate testDate2 = LocalDate.now().plusDays(5L);
        // within 5 days from now, return false
        ReminderDatePredicate fiveDays = new ReminderDatePredicate(5L);
        assertFalse(fiveDays.test(new OrderBuilder()
                .withDeliveryDate(testDate1.format(format1)).build()));

        // within 0 days from now, return true
        ReminderDatePredicate zeroDays = new ReminderDatePredicate(0L);
        assertFalse(zeroDays.test(new OrderBuilder()
                .withDeliveryDate(testDate2.format(format1)).build()));
    }
}
