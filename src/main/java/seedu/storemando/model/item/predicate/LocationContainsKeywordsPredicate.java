package seedu.storemando.model.item.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.storemando.commons.util.StringUtil;
import seedu.storemando.model.item.Item;

/**
 * Tests that a {@code Item}'s {@code location} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<Item> {
    /**
     * The list of keyword that is use to be filter on.
     */
    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
            .allMatch(keyword -> StringUtil.containsWord(item.getLocation().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof LocationContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((LocationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
