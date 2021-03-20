package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

public class Sex {

    public static final String MESSAGE_CONSTRAINTS = "Sex should only be M/Male/male or F/Female/female";
    public static final String VALIDATION_REGEX = "^male$|^female$|^Female$|^Male$|^M$|^F$";
    public final String value;

    /**
     * Constructs an {@code Sex}.
     *
     * @param sex A valid sex.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        value = sex;
    }

    /**
     * Returns true if a given string is a valid Sex.
     */
    public static boolean isValidSex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sex // instanceof handles nulls
                && value.equals(((Sex) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
