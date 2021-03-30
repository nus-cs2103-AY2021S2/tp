package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

import java.util.List;

public class PhoneNumberFilter extends AbstractFilter{
    public PhoneNumberFilter(String filterString) {
        super(filterString.trim());
    }

    @Override
    public boolean test(Customer customer) {
        return false;
    }

    @Override
    public List<Customer> filterAllCustomers(List<Customer> customer) {
        return null;
    }
}
