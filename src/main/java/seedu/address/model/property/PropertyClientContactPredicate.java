package seedu.address.model.property;

import java.util.function.Predicate;

import seedu.address.model.property.client.Contact;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientContactPredicate implements Predicate<Property> {
    public final Contact contact;

    public PropertyClientContactPredicate(String contact) throws IllegalArgumentException {
        this.contact = new Contact(contact);
    }

    @Override
    public boolean test(Property property) {
        return property.getClient() != null
               && this.contact.equals(property.getClient().getClientContact());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyClientContactPredicate // instanceof handles nulls
                && contact.equals(((PropertyClientContactPredicate) other).contact)); // state check
    }

}
