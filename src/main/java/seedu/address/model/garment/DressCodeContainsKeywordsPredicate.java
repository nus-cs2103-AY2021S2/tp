package seedu.address.model.garment;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.garment.ContainsKeywordsPredicate;
import seedu.address.model.garment.Garment;

import java.util.List;

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
