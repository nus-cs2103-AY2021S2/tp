package seedu.address.model.property;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PropertyContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(property.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyContainsKeywordsPredicate) other).keywords)); // state check
    }

}
