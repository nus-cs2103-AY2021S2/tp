package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class PropertyClientEmailPredicate implements Predicate<Property> {
    public final String email;

    /**
     * Constructs a PropertyClientEmailPredicate.
     */
    public PropertyClientEmailPredicate(String email) throws IllegalArgumentException {
        requireNonNull(email);
        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email given is empty. ");
        }
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
