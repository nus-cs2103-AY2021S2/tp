package seedu.address.logic.filters;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.customer.Customer;

/**
 * This class encapsulates a filter. However, no implementation of criterion on which to filter is given. This class
 * is an abstraction of any filter subclass which will have some specific criterion to filter {@code Customer}.
 */
public abstract class Filter implements Predicate<Customer> {
    protected final String filterString;

    /**
     * Creates a new {@code Filter} using the given filter string, which will be used to test a given {@code Customer }
     * based on specific criteria in the subclasses.
     *
     * @param filterString the filter string on which the customer will be matched
     */
    public Filter(String filterString) {
        Objects.requireNonNull(filterString);
        if (filterString.isEmpty()) {
            throw new IllegalArgumentException("Search string cannot be empty!");
        }

        this.filterString = filterString.trim();
    }

    @Override
    public String toString() {
        return filterString;
    }
}
