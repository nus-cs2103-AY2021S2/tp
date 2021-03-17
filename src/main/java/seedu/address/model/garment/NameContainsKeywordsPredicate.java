package seedu.address.model.garment;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Garment}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Garment garment) {
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(garment.getName().fullName, keyword));
    }

    /*@Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }*/
}
