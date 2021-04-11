package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

public class EmailFilter extends AbstractFilter {
    public EmailFilter(String filterString) {
        super(filterString.trim());
    }

    @Override public boolean test(Customer customer) {
        return customer.getEmail().getEmailIdWithoutDomain().toLowerCase().contains(filterString.toLowerCase());
    }
}
