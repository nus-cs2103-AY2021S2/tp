package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

import java.util.List;

public class Filters {
    public static AbstractFilter getCorrespondingFilter(String info) {
        return new AbstractFilter(info) {

            @Override
            public boolean test(Customer customer) {
                return false;
            }

            @Override
            public List<Customer> filterAllCustomers(List<Customer> customer) {
                return null;
            }
        };
    }
}
