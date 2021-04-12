package seedu.address.logic.filters;

import java.util.Objects;
import java.util.regex.Pattern;

import seedu.address.model.customer.Customer;

/**
 * This class filters based on phone number.
 */
public class PhoneNumberFilter extends Filter {

    /**
     * Takes in a string which represents the filter string for this object.
     *
     * @param filterString - the filter string using which to filter
     */
    public PhoneNumberFilter(String filterString) {
        super(filterString.trim());
        Objects.requireNonNull(filterString);
        if (!Pattern.matches("\\+?[0-9]+", this.filterString)) {
            throw new IllegalArgumentException("Phone number should only contain digits, possibly prefixed by a (+)");
        }
    }

    /**
     * Tests whether the given filterString used to create this object is contained inside the {@code PhoneNumber} of
     * the {@code Customer}.
     *
     * @param customer - the {@code Customer} object to test
     * @return - whether the customer passes the filter.
     */
    @Override
    public boolean test(Customer customer) {
        Objects.requireNonNull(customer);
        return customer.getPhone().getPhoneNumber().trim().contains(filterString);
    }
}
