package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.property.client.Contact.MESSAGE_CONSTRAINTS;
import static seedu.address.model.property.client.Contact.isValidContact;

import java.util.function.Predicate;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientContactPredicate implements Predicate<Property> {
    public final String contact;

    /**
     * Constructs a PropertyClientContactPredicate.
     */
    public PropertyClientContactPredicate(String contact) throws NullPointerException {
        requireNonNull(contact);
        checkArgument(isValidContact(contact), MESSAGE_CONSTRAINTS);
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
