package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Address} matches the address given
 * and {@code PostalCode} matches the postal given.
 * A pair of address and postal code uniquely identifies a property.
 */
public class PropertyAddressPostalCodePredicate implements Predicate<Property> {
    private final String address;
    private final String postal;

    /**
     * Constructs an {@code AppointmentDateTimePredicate}.
     *
     * @param address The address to be matched against.
     * @param postal The postal to be matched against.
     */
    public PropertyAddressPostalCodePredicate(String address, String postal) {
        this.address = address;
        this.postal = postal;
    }

    @Override
    public boolean test(Property property) {
        return property.getAddress().propertyAddress.equals(address)
                && property.getPostalCode().postal.equals(postal);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PropertyAddressPostalCodePredicate)) {
            return false;
        }
        PropertyAddressPostalCodePredicate otherPredicate = (PropertyAddressPostalCodePredicate) other;
        return address.equals(otherPredicate.address) && postal.equals(otherPredicate.postal);
    }

}
