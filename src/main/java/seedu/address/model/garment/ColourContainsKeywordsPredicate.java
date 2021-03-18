package seedu.address.model.garment;

import seedu.address.commons.util.StringUtil;

import java.util.List;

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
