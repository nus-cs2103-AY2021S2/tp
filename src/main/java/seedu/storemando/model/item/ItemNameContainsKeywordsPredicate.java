package seedu.storemando.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.storemando.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class ItemNameContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;
    private boolean isGenericFind;

    public ItemNameContainsKeywordsPredicate(List<String> keywords, boolean isGenericFind) {
        this.keywords = keywords;
        this.isGenericFind = isGenericFind;
    }

    @Override
    public boolean test(Item item) {
        if (isGenericFind) {
            return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(item.getItemName().fullName, keyword));
        } else {
            return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getItemName().fullName, keyword));
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ItemNameContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((ItemNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
