package seedu.address.model.garment;

import seedu.address.commons.util.StringUtil;

import java.util.List;

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
