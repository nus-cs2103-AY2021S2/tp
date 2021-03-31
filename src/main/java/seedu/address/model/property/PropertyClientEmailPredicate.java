package seedu.address.model.property;

import java.util.function.Predicate;

import seedu.address.model.property.client.Email;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientEmailPredicate implements Predicate<Property> {
    public final Email email;

    public PropertyClientEmailPredicate(String email) throws IllegalArgumentException {
        this.email = new Email(email);
    }

    @Override
    public boolean test(Property property) {
        return property.getClient() != null
               && this.email.equals(property.getClient().getClientEmail());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyClientEmailPredicate // instanceof handles nulls
                && email.equals(((PropertyClientEmailPredicate) other).email)); // state check
    }

}
