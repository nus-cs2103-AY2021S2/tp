package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's medical details in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMedicalDetails(String)}
 */
public class MedicalDetails {

    public static final String MESSAGE_CONSTRAINTS = "MedicalDetails can take any values, and it should not be blank."
            + "If there is nothing worthy of reporting, enter 'none'.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code MedicalDetails}.
     *
     * @param medicalDetails valid medical details.
     */
    public MedicalDetails(String medicalDetails) {
        requireNonNull(medicalDetails);
        checkArgument(isValidMedicalDetails(medicalDetails), MESSAGE_CONSTRAINTS);
        value = medicalDetails;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidMedicalDetails(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalDetails // instanceof handles nulls
                && value.equals(((MedicalDetails) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
