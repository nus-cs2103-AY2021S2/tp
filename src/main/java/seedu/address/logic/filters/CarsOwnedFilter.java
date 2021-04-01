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
     * @param filterString
     */
    public CarsOwnedFilter(String filterString) {
        super(filterString.trim());
        requireNonNull(filterString);
    }

    /**
     * Tests each car in CarsOwned, with the specified filters.
     *
     * @param customer
     * @return
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
