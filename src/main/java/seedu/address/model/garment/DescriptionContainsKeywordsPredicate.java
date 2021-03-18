package seedu.address.model.garment;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.description.Description;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Tests that a {@code Garment}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate extends ContainsKeywordsPredicate {

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Garment garment) {
        Set<Description> descriptions = garment.getDescriptions();
        Iterator<Description> descriptionIterator = descriptions.iterator();
        boolean result = false;
        while (descriptionIterator.hasNext()) {
            result = this.keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(descriptionIterator.next()
                            .descriptionName, keyword));
            if (result) {
                return true;
            }
        }
        return false;
    }
}
