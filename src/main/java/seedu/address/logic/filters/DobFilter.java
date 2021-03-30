package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

public class DobFilter extends AbstractFilter {
    private final String[] args;

    public DobFilter(String filterString) {
        super(filterString.trim());
        args = filterString.split(" ");
    }

    @Override
    public boolean test(Customer customer) {
        String dob = customer.getDateOfBirth().getBirthDate();
        for (var e : args) {
            if (dob.contains(e)) {
                return true;
            }
        }
        return false;
    }
}
