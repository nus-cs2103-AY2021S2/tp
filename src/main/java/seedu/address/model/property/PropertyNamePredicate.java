package seedu.address.model.property;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Property}'s {@code Name} matches any of the keywords given.
 */
public class PropertyNamePredicate implements Predicate<Property> {
    private final List<String> keywords;

    /**
     * Constructs a {@code PropertyNamePredicate}
     */
    public PropertyNamePredicate(List<String> names) {
        this.keywords = new ArrayList<>();
        for (String s : names) {
            this.keywords.add(s.toLowerCase());
        }
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(property.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyNamePredicate // instanceof handles nulls
                && keywords.equals(((PropertyNamePredicate) other).keywords)); // state check
    }

}
