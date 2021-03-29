package seedu.address.model.pool;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Passenger}'s {@code Name} matches any of the keywords given.
 */
public class DriverNameContainsKeywordsPredicate implements Predicate<Pool> {
    private final List<String> keywords;

    public DriverNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Pool pool) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(pool.getDriver().getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DriverNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DriverNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
