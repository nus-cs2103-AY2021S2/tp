package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

public class Faculty {

    private static final List<String> listFaculty = Arrays.asList("FASS", "BIZ", "COM", "SCALE", "DEN", "SDE", "DNUS",
            "ENG", "ISEP", "LAW", "MED", "MUSIC", "SPH", "SPP", "SCI", "USP", "YNC");

    public static final String MESSAGE_CONSTRAINTS = "The faculty entered should be one of the following: \n"
            + listFaculty.toString();

    public final String value;

    /**
     * Constructs an {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        value = faculty;
    }

    /**
     * Returns true if the given string is a valid faculty.
     *
     * @param test the string whose format is to be checked.
     * @return true if test is a valid faculty, false otherwise.
     */
    public static boolean isValidFaculty(String test) {
        return listFaculty.contains(test.toUpperCase());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && value.equals(((Faculty) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}