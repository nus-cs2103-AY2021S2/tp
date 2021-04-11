package seedu.address.logic.filters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;
import seedu.address.testutil.CustomerBuilder;

public class CoeExpiryFilterTest {
    private LocalDate now = LocalDate.now();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CoeExpiryFilter(null));
    }

    @Test
    public void constructor_invalidCoeExpiryFilter_throwsIllegalArgumentException() {
        String emptyString = "";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(emptyString));
        String invalidStringArgument = "today";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(invalidStringArgument));
        String negativeNumber = "-1234";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(negativeNumber));
    }

    @Test
    public void isValidFilter() {
        // null filter
        assertThrows(NullPointerException.class, () -> CoeExpiryFilter.isValidFilter(null));

        // invalid filter
        assertFalse(CoeExpiryFilter.isValidFilter("")); // empty string
        assertFalse(CoeExpiryFilter.isValidFilter(" ")); // spaces only
        assertFalse(CoeExpiryFilter.isValidFilter("today")); // wrong string
        assertFalse(CoeExpiryFilter.isValidFilter("-1")); // negative number

        // valid filter
        assertTrue(CoeExpiryFilter.isValidFilter("exp")); // special keyword
        assertTrue(CoeExpiryFilter.isValidFilter("0")); // zero is valid
        assertTrue(CoeExpiryFilter.isValidFilter("3")); // long address
    }

    @Test
    public void test_nullCustomer_throwsNullPointerException() {
        CoeExpiryFilter filter = new CoeExpiryFilter("exp");
        assertThrows(NullPointerException.class, () -> filter.test(null));
    }

    @Test
    public void test_expiredCustomer_true() {
        CoeExpiryFilter filterToday = new CoeExpiryFilter("exp");
        String yesterdayString = now.minusDays(1).format(CoeExpiry.DATE_TIME_FORMATTER);
        String oneYearString = now.plusYears(1).format(CoeExpiry.DATE_TIME_FORMATTER);
        Customer alice = new CustomerBuilder()
                .withAdditionalCar("Honda", "Civic", yesterdayString)
                .withAdditionalCar("Honda", "Civic", oneYearString)
                .build();
        assertTrue(filterToday.test(alice));

        CoeExpiryFilter filterOneYear = new CoeExpiryFilter("2");
        Customer bob = new CustomerBuilder()
                .withAdditionalCar("Honda", "Civic", oneYearString)
                .build();
        assertTrue(filterOneYear.test(bob));
    }

    @Test
    public void test_freshCustomer_false() {
        CoeExpiryFilter filter = new CoeExpiryFilter("1");
        String twoYearString = now.plusYears(2).format(CoeExpiry.DATE_TIME_FORMATTER);
        String oneYearString = now.plusYears(1).format(CoeExpiry.DATE_TIME_FORMATTER);
        Customer alice = new CustomerBuilder()
                .withAdditionalCar("Honda", "Civic", twoYearString)
                .withAdditionalCar("Honda", "Civic", oneYearString)
                .build();
        assertFalse(filter.test(alice));
    }

    @Test
    public void test_noCarCustomer_false() {
        CoeExpiryFilter filter = new CoeExpiryFilter("exp");
        Customer alice = new CustomerBuilder().build();
        assertFalse(filter.test(alice));
    }

    @Test
    public void equals() {
        CoeExpiryFilter immediateFilter = new CoeExpiryFilter("exp");
        CoeExpiryFilter oneYearFilter = new CoeExpiryFilter("1");

        // same object -> returns true
        assertTrue(immediateFilter.equals(immediateFilter));

        // same values -> returns true
        CoeExpiryFilter immediateFilterCopy = new CoeExpiryFilter("exp");
        assertTrue(immediateFilter.equals(immediateFilterCopy));

        // different types -> returns false
        assertFalse(immediateFilter.equals(1));

        // null -> returns false
        assertFalse(immediateFilter.equals(null));

        // different customer -> returns false
        assertFalse(immediateFilter.equals(oneYearFilter));
    }
}
