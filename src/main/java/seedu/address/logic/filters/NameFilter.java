package seedu.address.logic.filters;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

import seedu.address.model.customer.Customer;

/**
 * This class encapsulates the filter used for filtering {@code Customer} by name. It checks against multiple names
 * given via a {@code filterString} and the {@code Customer} is matched if the {@code Customer} name matches with any of
 * the given names. Here, matching is spelling-tolerant to some extent in that it matches using subsequence -- in
 * particular, {@code abbbc} is matched to {@code abc} but not to {@code acb}.
 */
public class NameFilter extends Filter {
    public static final String MESSAGE_CONSTRAINTS = "Name Filter must contain at least one valid name to test by";
    /*
     * The first character of the search string must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private final String[] nameList;

    /**
     * Creates a filter that filters by {@code Name}, with tolerances for mis-spelling.
     *
     * @param nameListSingleString - String with names to search, separated by spaces.
     */
    public NameFilter(String nameListSingleString) {
        super(nameListSingleString);
        requireNonNull(nameListSingleString);
        checkArgument(isValidFilter(nameListSingleString), MESSAGE_CONSTRAINTS);
        this.nameList = nameListSingleString.split("\\s+");
    }

    /**
     * Checks whether the filter string given is of the acceptable form for a name filter.
     *
     * @param filterString - the filter string whose validity is to be checked
     * @return - whether the filter string is valid
     */
    public static boolean isValidFilter(String filterString) {
        return filterString.matches(VALIDATION_REGEX);
    }

    /**
     * Checks whether the second string is a subsequence of the first string. Here the first string represents the given
     * customer name whereas the second string corresponds to the given filter name.
     *
     * @param customerName  - the actual customer name
     * @param potentialName - the filter string against which to check whether the customer name matches
     * @return - whether there is a match
     */
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

    /**
     * {@inheritDoc} Checks whether any of the given names in the filterString matches the {@code Customer} name for the
     * particular {@code Customer} being tested.
     *
     * @param customer - the customer should to be tested
     * @return - whether there is a match
     */
    @Override
    public boolean test(Customer customer) {
        requireNonNull(customer);
        String[] customerNameTokens = customer.getName().fullName.split("\\s+");
        return Arrays.stream(nameList)
            .allMatch(searchToken -> testSearchToken(searchToken, customerNameTokens));
    }
}
