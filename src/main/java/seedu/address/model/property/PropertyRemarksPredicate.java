package seedu.address.model.property;

import java.util.function.Predicate;

import seedu.address.model.remark.Remark;

/**
 * Tests that a {@code Property}'s {@code Remarks} matches the remark given.
 */
public class PropertyRemarksPredicate implements Predicate<Property> {
    private final Remark keywords;

    public PropertyRemarksPredicate(String keywords) throws IllegalArgumentException {
        this.keywords = new Remark(keywords);
    }

    @Override
    public boolean test(Property property) {
        return this.keywords.equals(property.getRemarks());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyRemarksPredicate // instanceof handles nulls
                && keywords.equals(((PropertyRemarksPredicate) other).keywords)); // state check
    }

}
