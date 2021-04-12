package seedu.address.logic;

import java.util.function.Predicate;

import seedu.address.model.customer.Customer;

public class ExactMatchPredicate implements Predicate<Customer> {

    private final Customer customerToMatch;

    public ExactMatchPredicate(Customer customer) {
        this.customerToMatch = customer;
    }

    @Override
    public boolean test(Customer customer) {
        return customer.equals(customerToMatch);
    }
}
