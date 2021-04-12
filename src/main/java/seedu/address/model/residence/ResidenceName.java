package seedu.address.model.residence;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.name.CommonName;

/**
 * Represents a Residence's name in ResidenceTracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidResidenceName(String)}
 */
public class ResidenceName extends CommonName {

    public static final String MESSAGE_CONSTRAINTS =
            "Residence Names should only contain 1 or more alphanumeric characters, 0 or more spaces, and "
            + "\n0 or more '@'. Residence Names should not be blank.";

    /*
     * The first character of the Residence's name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][@\\p{Alnum} ]*";

    /**
     * Constructs a {@code ResidenceName}.
     *
     * @param name A valid residence name.
     */
    public ResidenceName(String name) {
        requireNonNull(name);
        checkArgument(isValidResidenceName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid residence name.
     */
    public static boolean isValidResidenceName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns value of this {@code ResidenceName}.
     */
    public String getValue() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidenceName // instanceof handles nulls
                && fullName.equals(((ResidenceName) other).fullName)); // state check
    }
}
