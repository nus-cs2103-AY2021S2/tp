package seedu.address.model.garment;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Garment}'s {@code Colour} matches any of the keywords given.
 */
public class ColourContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    public ColourContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Garment garment) {
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(garment.getColour().colour, keyword));
    }
}
