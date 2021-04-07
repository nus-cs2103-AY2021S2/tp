package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code PostalCode} matches the postal given.
 */
public class PropertyPostalCodePredicate implements Predicate<Property> {
    private final String postal;

    public PropertyPostalCodePredicate(String postal) {
        this.postal = postal;
    }

    @Override
    public boolean test(Property property) {
        return property.getPostalCode().postal.contains(this.postal);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyPostalCodePredicate // instanceof handles nulls
                && postal.equals(((PropertyPostalCodePredicate) other).postal)); // state check
    }

}
