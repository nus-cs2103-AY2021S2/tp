package seedu.address.logic.filters;

import java.util.Objects;

import seedu.address.model.customer.Customer;

/**
 * This class is used to filter for the {@code Email} field of the {@code Customer}, based on checking whether the given
 * filter string is contained inside the emailID.
 */
public class EmailFilter extends Filter {

    /**
     * Creates a constructor for EmailFilter using a filter string.
     *
     * @param filterString the string against which to parse
     */
    public EmailFilter(String filterString) {
        super(filterString.trim());
        Objects.requireNonNull(filterString);
    }

    /**
     * This function checks whether the {@code Email} field contains the {@code filterString}, given in the constructor
     * while creating this object, as a substring.
     *
     * @param customer the customer to test for
     * @return whether the email contains the filterString
     */
    @Override
    public boolean test(Customer customer) {
        Objects.requireNonNull(customer);
        return customer.getEmail().value.toLowerCase().contains(filterString.toLowerCase());
    }
}
