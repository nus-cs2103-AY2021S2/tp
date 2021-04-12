package seedu.address.logic.filters;

import static java.util.Objects.requireNonNull;

import seedu.address.model.customer.Customer;

/**
 * Filters CarsOwned based on user specified filters carBrand, carType.
 */
public class CarsPreferredFilter extends CarFilter {
    /**
     * Creates a filter for CarsOwned.
     *
     * @param filterString - the filterString against which to match the customers
     */
    public CarsPreferredFilter(String filterString) {
        super(filterString.trim());
        requireNonNull(filterString);
    }

    /**
     * Tests each car in CarsOwned, with the specified filters.
     *
     * @param customer - the customer to be matched
     * @return - whether the customer is matched
     */
    @Override
    public boolean test(Customer customer) {
        requireNonNull(customer);
        return customer.getCarsPreferred()
                .parallelStream()
                .anyMatch(x -> carFilterPredicate(x, filterString));
    }
}
