package seedu.address.model.appointment;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.property.Property;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PropertyNameContainsKeywordsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyNameContainsKeywordsPredicate(List<String> keywords) {
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
                || (other instanceof PropertyNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
