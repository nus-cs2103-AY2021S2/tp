package seedu.us.among.model.endpoint;

import java.util.List;
import java.util.function.Predicate;

import seedu.us.among.commons.util.StringUtil;

/**
 * Tests that a {@code Endpoint}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Endpoint> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Endpoint endpoint) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(endpoint.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
