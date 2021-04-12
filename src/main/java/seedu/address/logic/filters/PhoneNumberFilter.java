package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

import java.util.Objects;
import java.util.regex.Pattern;

public class PhoneNumberFilter extends Filter {
    public PhoneNumberFilter(String filterString) {
        super(filterString.trim());
        if (!Pattern.matches("\\+?[0-9]+", this.filterString)) {
            throw new IllegalArgumentException("Phone number should only contain digits, possibly prefixed by a (+)");
        }
    }

    @Override
    public boolean test(Customer customer) {
        Objects.requireNonNull(customer);
        return customer.getPhone().getPhoneNumber().trim().contains(filterString);
    }
}
