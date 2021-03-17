package seedu.address.model.insurance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of a client's insurance plan.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class InsurancePlanName {

    public static final String MESSAGE_CONSTRAINTS =
            "The insurance plan name should only contain alphanumeric characters and spaces,"
                    + "and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code InsurancePlanName}.
     *
     * @param name A valid name.
     */
    public InsurancePlanName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePlanName // instanceof handles nulls
                && name.equals(((InsurancePlanName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
