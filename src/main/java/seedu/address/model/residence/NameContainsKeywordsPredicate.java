package seedu.address.model.residence;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Residence}'s {@code ResidenceName} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Residence> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Residence residence) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(residence.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
