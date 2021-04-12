package seedu.address.logic.filters;

import java.util.Objects;

import seedu.address.model.customer.Customer;

/**
 * This class is used to filter for the {@code Address} field of the {@code Customer}, based on checking whether the
 * given filter string is contained inside the address.
 */
public class AddressFilter extends Filter {
    public AddressFilter(String filterString) {
        super(filterString.trim());
    }

    /**
     * This function checks whether the {@code Address} field contains the {@code filterString}, given in the
     * constructor while creating this object, as a substring.
     *
     * @param customer - the customer to test for
     * @return - whether the address contains the filterString
     */
    @Override
    public boolean test(Customer customer) {
        Objects.requireNonNull(customer);
        return customer.getAddress().value.trim().contains(filterString.trim());
    }
}
