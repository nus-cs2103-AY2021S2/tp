package seedu.address.logic.filters;

import java.time.LocalDate;
import java.util.List;

import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;

public class CoeExpiryFilter extends AbstractFilter {
    private final LocalDate coeMinimumDeadline;

    /**
     * Create a filter for COE Expiry, checking whether a COE
     * expires in {@code filterString} years or less.
     *
     * A special keyword "exp" is accepted as an alias for COE
     * that expires in zero years or less (ie: Already expired).
     *
     * TODO: Include how this will throw an exception upon getting negative years
     *
     * @param filterString The number of years that a COE can be valid for before
     *                     being skipped.
     */
    public CoeExpiryFilter(String filterString) {
        super(filterString);
        LocalDate currentDate = LocalDate.now();
        boolean expiredOnly = filterString.equals("exp");
        int years = 0;
        if (!expiredOnly) {
            years = Integer.parseInt(filterString);
        }
        if (years < 0) {
            throw new Exception(); //TODO: Figure out the correct exception
        }
        this.coeMinimumDeadline = currentDate.plusYears(years);
    }
    private boolean testCoeExpiry(CoeExpiry coeExpiry) {
        return !coeMinimumDeadline.isAfter(coeExpiry.toDate());
    }

    @Override
    public boolean test(Customer customer) {
        return customer.getCarsOwned()
                       .values()
                       .parallelStream()
                       .anyMatch(this::testCoeExpiry);
    }

    @Override
    public List<Customer> filterAllCustomers(List<Customer> customer) {
        return null;
    }
}
