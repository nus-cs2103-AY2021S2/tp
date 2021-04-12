package seedu.us.among.model.endpoint;

import java.util.List;
import java.util.function.Predicate;

import seedu.us.among.commons.util.StringUtil;

/**
 * Tests that a {@code Endpoint}'s {@code Name} matches any of the keywords
 * given.
 */
public class EndPointContainsKeywordsPredicate implements Predicate<Endpoint> {
    private final List<String> keywords;

    public EndPointContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Endpoint endpoint) {
        //Currently allows for checking of PartialWords in method, address, data, tags and headers.
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsPartialWordIgnoreCase(endpoint.getMethod().methodName, keyword)
                        || StringUtil.containsPartialWordIgnoreCase(endpoint.getTags().toString(), keyword)
                        || StringUtil.containsPartialWordIgnoreCase(endpoint.getAddress().value, keyword)
                        || StringUtil.containsPartialWordIgnoreCase(endpoint.getData().value, keyword)
                        || StringUtil.containsPartialWordIgnoreCase(endpoint.getHeaders().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndPointContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((EndPointContainsKeywordsPredicate) other).keywords)); // state check
    }

}
