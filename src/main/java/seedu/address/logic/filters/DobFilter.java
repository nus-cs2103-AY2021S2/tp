package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

public class DobFilter extends AbstractFilter {
    private final String[] args;

    /**
     * Constructor for DoBFilter, creates a list of keywords in the date, to later match with the customer's DOB.
     * @param filterString - the birthdate to match. No particular format needed.
     */
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
