package seedu.address.model.garment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.description.Description;

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
        ArrayList<Description> descriptionList = new ArrayList<>(descriptions);
        String descriptionString = "";
        for (Description d : descriptionList) {
            descriptionString += d.descriptionName + " ";
        }
        String s = descriptionString;
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        s, keyword));
    }
}
