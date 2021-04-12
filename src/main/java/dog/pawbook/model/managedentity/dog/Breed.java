//@@author branzuelajohn
package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Dog's breed in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidBreed(String)}
 */
public class Breed {

    public static final String MESSAGE_CONSTRAINTS = "Breeds should only contain alphabet characters and spaces,"
            + " and it should not be blank.";
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";

    public final String value;

    /**
     * Constructs an {@code Breed}.
     *
     * @param breed A valid breed.
     */
    public Breed(String breed) {
        requireNonNull(breed);
        checkArgument(isValidBreed(breed), MESSAGE_CONSTRAINTS);
        value = breed;
    }

    /**
     * Returns true if a given string is a valid breed.
     */
    public static boolean isValidBreed(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Breed // instanceof handles nulls
                && value.equals(((Breed) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
