package seedu.address.logic.filters;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.customer.Customer;

public abstract class Filter {
    protected final String filterString;

    public Filter(String filterString) {
        Objects.requireNonNull(filterString);
        if (filterString.isEmpty()) {
            throw new IllegalArgumentException("Search string cannot be empty!");
        }

        this.filterString = filterString.trim();
    }

    public abstract boolean test(Customer customer);

    public List<Customer> filterAllCustomers(List<Customer> customers) {
        return customers.stream().filter(this::test).collect(Collectors.toUnmodifiableList());
    }

    public String toString() {
        return filterString;
    }
}
