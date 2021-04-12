package seedu.us.among.model.endpoint;

import java.util.List;
import java.util.function.Predicate;

import seedu.us.among.commons.util.StringUtil;

/**
 * Tests that a {@code Endpoint}'s {@code Name} matches any of the keywords
 * given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Endpoint> {
    private final List<String> keywords;

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Endpoint endpoint) {
        //Currently allows for checking of PartialWords in method, data, tags and headers.
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsPartialWordIgnoreCase(endpoint.getAddress().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((AddressContainsKeywordsPredicate) other).keywords)); // state check
    }

}
