package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Remarks} matches the remark given.
 */
public class PropertyRemarksPredicate implements Predicate<Property> {
    private final String keywords;

    public PropertyRemarksPredicate(String keywords) throws IllegalArgumentException {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Property property) {
        return property.getRemarks().remark.toLowerCase().contains(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyRemarksPredicate // instanceof handles nulls
                && keywords.equals(((PropertyRemarksPredicate) other).keywords)); // state check
    }

}
