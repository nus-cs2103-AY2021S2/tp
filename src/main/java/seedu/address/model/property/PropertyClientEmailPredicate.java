package seedu.address.model.property;

import java.util.function.Predicate;

import seedu.address.model.property.client.Email;

import static java.util.Objects.requireNonNull;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientEmailPredicate implements Predicate<Property> {
    public final String email;

    public PropertyClientEmailPredicate(String email) throws IllegalArgumentException {
        requireNonNull(email);
        this.email = email;
    }

    @Override
    public boolean test(Property property) {
        return property.getClient() != null
               && property.getClient().getClientEmail().email.toLowerCase().contains(this.email.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyClientEmailPredicate // instanceof handles nulls
                && email.equals(((PropertyClientEmailPredicate) other).email)); // state check
    }

}
