package seedu.address.model.garment;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Garment}'s {@code DressCode} matches any of the keywords given.
 */
public class DressCodeContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    public DressCodeContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Garment garment) {
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(garment.getDressCode().value, keyword));
    }
}
