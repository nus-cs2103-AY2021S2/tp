package seedu.address.model.garment;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Garment}'s {@code Size} matches any of the keywords given.
 */
public class SizeContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    public SizeContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Garment garment) {
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(garment.getSize().value, keyword));
    }
}
