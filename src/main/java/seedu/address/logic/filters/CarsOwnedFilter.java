package seedu.address.logic.filters;

import static java.util.Objects.requireNonNull;

import seedu.address.model.customer.Customer;

/**
 * Filters CarsOwned based on user specified filters carBrand, carType.
 */
public class CarsOwnedFilter extends CarFilter {
    /**
     * Creates a filter for CarsOwned.
     *
     * @param filterString the filter against which to match
     */
    public CarsOwnedFilter(String filterString) {
        super(filterString.trim());
        requireNonNull(filterString);
    }

    /**
     * Tests each car in CarsOwned, with the specified filters.
     *
     * @param customer the customer to be tested with the filter
     * @return true if the customer passes the filter, false otherwise
     */
    @Override
    public boolean test(Customer customer) {
        requireNonNull(customer);
        return customer.getCarsOwned()
                .keySet()
                .parallelStream()
                .anyMatch(x -> carFilterPredicate(x, filterString));
    }
}
