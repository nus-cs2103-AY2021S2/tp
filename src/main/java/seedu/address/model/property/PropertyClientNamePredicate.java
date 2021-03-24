package seedu.address.model.property;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.property.client.Client;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientNamePredicate implements Predicate<Property> {
    private final List<String> keywords;

    public PropertyClientNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Property property) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(property.getClient().getClientName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyClientNamePredicate // instanceof handles nulls
                && keywords.equals(((PropertyClientNamePredicate) other).keywords)); // state check
    }

}
