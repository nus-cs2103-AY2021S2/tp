package seedu.address.logic.filters;

import java.util.List;

import seedu.address.model.customer.Customer;

public abstract class AbstractFilter {
    protected final String filterString;

    public AbstractFilter(String filterString) {
        this.filterString = filterString;
    }

    public abstract boolean test(Customer customer);

    public abstract List<Customer> filterAllCustomers(List<Customer> customer);
}
