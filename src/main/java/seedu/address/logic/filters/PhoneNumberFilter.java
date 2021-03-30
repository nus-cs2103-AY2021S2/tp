package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

public class PhoneNumberFilter extends AbstractFilter {
    public PhoneNumberFilter(String filterString) {
        super(filterString.trim());
    }

    @Override
    public boolean test(Customer customer) {
        return customer.getPhone().getPhoneNumber().trim().startsWith(filterString);
    }
}
