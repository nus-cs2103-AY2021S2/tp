package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.expirydate.predicate.ItemExpiringPredicate;
import seedu.storemando.testutil.ItemBuilder;

public class ItemExpiringPredicateTest {

    @Test
    public void equals() {
        long firstPredicateNumOfDays = 3;
        long secondPredicateNumOfDays = 7;

        ItemExpiringPredicate firstPredicate =
            new ItemExpiringPredicate((firstPredicateNumOfDays));
        ItemExpiringPredicate secondPredicate =
            new ItemExpiringPredicate((secondPredicateNumOfDays));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ItemExpiringPredicate firstPredicateCopy =
            new ItemExpiringPredicate(firstPredicateNumOfDays);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_daysToExpiryDate() {
        // Item expiring in 3 days and search for items within 3 days
        ItemExpiringPredicate predicate = new ItemExpiringPredicate((long) 3);
        LocalDate threeDayAfterToday = LocalDate.now().plusDays(3);

        //item expiring exactly 3 days from today
        assertTrue(predicate.test(new ItemBuilder().withExpiryDate(threeDayAfterToday.toString()).build()));

        //item expiring exactly today
        assertTrue(predicate.test(new ItemBuilder().withExpiryDate(LocalDate.now().toString()).build()));

        //item already expired
        assertTrue(predicate.test(new ItemBuilder().withExpiryDate("2020-10-10").build()));

        //item expiring 4 days from today
        assertFalse(predicate.test(new ItemBuilder().withExpiryDate(threeDayAfterToday.plusDays(1).toString())
            .build()));

        // Item expiring in 1 week and search for items within 3 days
        LocalDate oneWeekAfterToday = LocalDate.now().plusDays(7);
        assertFalse(predicate.test(new ItemBuilder().withExpiryDate(oneWeekAfterToday.toString()).build()));
    }

    @Test
    public void test_itemWithoutExpiringDate() {
        ItemExpiringPredicate predicate = new ItemExpiringPredicate((long) 3);
        assertFalse(predicate.test(new ItemBuilder().withExpiryDate("No Expiry Date").build()));
    }
}
