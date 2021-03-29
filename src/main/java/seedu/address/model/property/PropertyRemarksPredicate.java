package seedu.address.model.property;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.remark.Remark;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Name} matches any of the keywords given.
 */
public class PropertyRemarksPredicate implements Predicate<Property> {
    private final Remark keywords;

    public PropertyRemarksPredicate(String keywords) {
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
