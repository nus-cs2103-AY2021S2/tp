package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class DateViewPredicateTest {

    @Test
    public void equals() {
        AppointmentDateTime firstPredicateDateTime = new AppointmentDateTime("2021-03-24 10:00AM");
        AppointmentDateTime secondPredicateDateTime = new AppointmentDateTime("2021-03-24 12:00PM");

        DateViewPredicate firstPredicate = new DateViewPredicate(firstPredicateDateTime);
        DateViewPredicate secondPredicate = new DateViewPredicate(secondPredicateDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DateViewPredicate firstPredicateCopy = new DateViewPredicate(firstPredicateDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different appointment -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        AppointmentDateTime firstPredicateDateTime = new AppointmentDateTime("2021-03-24 10:00AM");
        DateViewPredicate predicate = new DateViewPredicate(firstPredicateDateTime);
        assertTrue(predicate.test(new AppointmentBuilder().withTimeFrom("2021-03-24 10:00AM").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching date
        DateViewPredicate predicate = new DateViewPredicate(new AppointmentDateTime("2021-03-30 12:00PM"));
        assertFalse(predicate.test(new AppointmentBuilder().withTimeFrom("2021-03-24 12:00PM").build()));

        // Non-matching date in different format
        predicate = new DateViewPredicate(new AppointmentDateTime("2021/03/12 12:00PM"));
        assertFalse(predicate.test(new AppointmentBuilder().withTimeFrom("2021/03/30 12:00PM").build()));
    }
}
