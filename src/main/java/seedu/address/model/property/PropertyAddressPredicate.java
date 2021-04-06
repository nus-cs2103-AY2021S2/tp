package seedu.address.model.property;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Tests that a {@code Property}'s {@code Address} matches the address given.
 */
public class PropertyAddressPredicate implements Predicate<Property> {
    private final String address;

    public PropertyAddressPredicate(String address) {
        requireNonNull(address);
        this.address = address;
    }

    @Override
    public boolean test(Property property) {
        return property.getAddress().propertyAddress.contains(address);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyAddressPredicate // instanceof handles nulls
                && address.equals(((PropertyAddressPredicate) other).address)); // state check
    }

}
