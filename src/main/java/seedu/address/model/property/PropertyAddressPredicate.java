package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Address} matches the address given.
 */
public class PropertyAddressPredicate implements Predicate<Property> {
    private final String address;

    /**
     * Constructs a PropertyAddressPredicate.
     */
    public PropertyAddressPredicate(String address) throws IllegalArgumentException {
        requireNonNull(address);
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address given is empty. ");
        }
        this.address = address;
    }

    @Override
    public boolean test(Property property) {
        return property.getAddress().propertyAddress.toLowerCase().contains(address.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyAddressPredicate // instanceof handles nulls
                && address.equals(((PropertyAddressPredicate) other).address)); // state check
    }

}
