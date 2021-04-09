package seedu.address.model.property;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientNamePredicate implements Predicate<Property> {
    private final List<String> keywords;

    /**
     * Constructs a {@code PropertyClientNamePredicate} given a keyword.
     * @throws IllegalArgumentException when keyword is empty.
     */
    public PropertyClientNamePredicate(List<String> keywords) throws IllegalArgumentException {
        this.keywords = new ArrayList<>();
        for (String s : keywords) {
            if (s.trim().isEmpty()) {
                throw new IllegalArgumentException("Client name given is empty. ");
            }
            this.keywords.add(s);
        }
    }

    @Override
    public boolean test(Property property) {
        return property.getClient() != null
               && keywords.stream()
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
