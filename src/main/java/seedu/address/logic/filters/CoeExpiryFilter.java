package seedu.address.logic.filters;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;

public class CoeExpiryFilter extends Filter {
    public static final String MESSAGE_CONSTRAINTS = "COE Expiry Filter can only accept 'exp' or a non negative number";
    private final LocalDate coeExpiryThreshold;

    /**
     * Create a filter for COE Expiry, checking whether a COE
     * expires in {@code filterString} years or less.
     *
     * A special keyword "exp" is accepted as an alias for COE
     * that expires in zero years or less (ie: Already expired).
     *
     * Note that {@code filterString} must either be "exp" or a non negative number.
     *
     * @param filterString The number of years that a COE can be valid for before
     *                     being skipped.
     */
    public CoeExpiryFilter(String filterString) {
        super(filterString);
        requireNonNull(filterString);
        checkArgument(isValidFilter(filterString), MESSAGE_CONSTRAINTS);
        LocalDate currentDate = LocalDate.now(); // Should be the date without time
        System.out.println(filterString);
        boolean expiredOnly = filterString.equals("exp");
        int years = 0;
        if (!expiredOnly) {
            years = Integer.parseInt(filterString);
        }
        this.coeExpiryThreshold = currentDate.plusYears(years);
    }
    /**
     * Returns true if a given string is a valid filter.
     */
    public static boolean isValidFilter(String filterString) {
        if (filterString.equals("exp")) {
            return true;
        }
        try {
            return Integer.parseInt(filterString) >= 0;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private boolean isCoeExpiryExpiringSoon(CoeExpiry coeExpiry) {
        return coeExpiryThreshold.isAfter(coeExpiry.toDate());
    }

    @Override
    public boolean test(Customer customer) {
        requireNonNull(customer);
        return customer.getCarsOwned()
                       .values()
                       .parallelStream()
                       .anyMatch(this::isCoeExpiryExpiringSoon);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CoeExpiryFilter // instanceof handles nulls
                && coeExpiryThreshold.equals(((CoeExpiryFilter) other).coeExpiryThreshold)); // state check
    }
}
