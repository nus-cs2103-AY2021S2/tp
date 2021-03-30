package seedu.address.logic.filters;

import org.junit.jupiter.api.Test;
import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;
import seedu.address.testutil.CustomerBuilder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class CoeExpiryFilterTest { //TODO: Complete this test collection
    LocalDate now = LocalDate.now();

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
        CoeExpiryFilter filter = new CoeExpiryFilter("exp");
        String yesterdayString = now.minusDays(1).format(CoeExpiry.DATE_TIME_FORMATTER);
        String oneYearString = now.plusYears(1).format(CoeExpiry.DATE_TIME_FORMATTER);
        Customer alice = new CustomerBuilder()
                .withAdditionalCar("Civic+Honda", yesterdayString)
                .withAdditionalCar("Civic+Honda", oneYearString)
                .build();
        assertTrue(filter.test(alice));
    } //TODO: Add more test cases testing the function test

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
