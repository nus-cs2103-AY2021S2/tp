package seedu.address.logic.filters;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

import seedu.address.model.customer.Customer;


public class NameFilter extends AbstractFilter {
    public static final String MESSAGE_CONSTRAINTS = "Name Filter must contain at least one valid name to test by";
    /*
     * The first character of the search string must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private static final int MISTAKE_THRESHOLD = 3;

    private final String[] nameList;

    /**
     * Creates a filter that filters by name, with tolerances for mis-spelling.
     *
     * @param nameListSingleString String with names to search, separated by spaces.
     */
    public NameFilter(String nameListSingleString) {
        super(nameListSingleString);
        requireNonNull(nameListSingleString);
        checkArgument(isValidFilter(nameListSingleString), MESSAGE_CONSTRAINTS);
        this.nameList = nameListSingleString.split("\\s+");
    }

    /**
     * Returns true if a given string is a valid filter.
     */
    public static boolean isValidFilter(String filterString) {
        return filterString.matches(VALIDATION_REGEX);
    }
    private boolean isSubsequence(String customerName, String potentialName) {
        int customerPointer = 0;
        int potentialPointer = 0;
        while (customerPointer < customerName.length() && potentialPointer < potentialName.length()) {
            if (customerName.charAt(customerPointer) == potentialName.charAt(potentialPointer)) {
                potentialPointer++;
            }

            customerPointer++;
        }

        return potentialPointer == potentialName.length();
    }
    private boolean testSearchToken(String searchToken, String[] customerTokenList) {
        return Arrays.stream(customerTokenList)
                     .anyMatch(customerToken -> (isSubsequence(customerToken, searchToken)));
    }

    @Override
    public boolean test(Customer customer) {
        requireNonNull(customer);
        String[] customerNameTokens = customer.getName().fullName.split("\\s+");
        return Arrays.stream(nameList)
                     .allMatch(searchToken -> testSearchToken(searchToken, customerNameTokens));
    }
}
