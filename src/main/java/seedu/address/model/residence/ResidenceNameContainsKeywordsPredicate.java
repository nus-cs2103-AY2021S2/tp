package seedu.address.model.residence;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code ResidenceName} matches any of the keywords given.
 */
public class ResidenceNameContainsKeywordsPredicate implements Predicate<Residence> {
    private final List<String> keywords;

    public ResidenceNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Residence residence) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        residence.getResidenceName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidenceNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ResidenceNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
