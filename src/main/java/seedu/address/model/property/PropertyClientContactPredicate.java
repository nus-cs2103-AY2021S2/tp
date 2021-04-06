package seedu.address.model.property;

import java.util.function.Predicate;

import seedu.address.model.property.client.Contact;

import static java.util.Objects.requireNonNull;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientContactPredicate implements Predicate<Property> {
    public final String contact;

    public PropertyClientContactPredicate(String contact) throws IllegalArgumentException {
        requireNonNull(contact);
        this.contact = contact;
    }

    @Override
    public boolean test(Property property) {
        return property.getClient() != null
               && property.getClient().getClientContact().contact.contains(this.contact);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyClientContactPredicate // instanceof handles nulls
                && contact.equals(((PropertyClientContactPredicate) other).contact)); // state check
    }

}
