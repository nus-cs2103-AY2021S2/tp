package seedu.address.model.garment;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Garment}'s {@code DressCode} matches any of the keywords given.
 */
public class TypeContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    public TypeContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Garment garment) {
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(garment.getType().value, keyword));
    }
}
