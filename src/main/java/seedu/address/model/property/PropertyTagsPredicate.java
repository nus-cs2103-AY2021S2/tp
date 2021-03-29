package seedu.address.model.property;

import seedu.address.commons.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Name} matches any of the keywords given.
 */
public class PropertyTagsPredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyTagsPredicate(String keyword) {
        this.keywords = Arrays.asList(keyword.split(","));
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(property.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTagsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyTagsPredicate) other).keywords)); // state check
    }

}
