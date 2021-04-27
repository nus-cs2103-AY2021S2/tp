package seedu.student.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.student.commons.util.AppUtil.checkArgument;

public class MatriculationNumber {

    public static final String MESSAGE_CONSTRAINTS = "Matriculation numbers should be of the format A + "
            + "7 digit numeric sequence + alphabet.";

    private static final String FIRST_CHARACTER = "A";

    private static final String NUMERIC_SEQUENCE = "\\d{7}";

    private static final String LAST_CHARACTER = "[A-Z]";

    public static final String VALIDATION_REGEX = FIRST_CHARACTER + NUMERIC_SEQUENCE + LAST_CHARACTER;

    public final String value;

    /**
     * Constructor for a MatriculationNumber object.
     * @param matriculationNumber The string of the matriculation number.
     */
    public MatriculationNumber(String matriculationNumber) {
        requireNonNull(matriculationNumber);
        checkArgument(isValidMatric(matriculationNumber), MESSAGE_CONSTRAINTS);
        value = matriculationNumber;
    }

    /**
     * Returns if a given string is a valid matriculation number.
     */
    public static boolean isValidMatric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumber // instanceof handles nulls
                && value.equals(((MatriculationNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
