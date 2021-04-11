package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;
/**
 * Represents a Student's school in TutorsPet.
 * Guarantees: immutable; is valid as declared in {@link #isValidSchool(String)}
 */
public class School implements Comparable<School> {

    public static final int MAX_CHARACTERS = 60;
    public static final String MESSAGE_CONSTRAINTS =
            "Schools should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String INVALID_LENGTH_MESSAGE =
            "ERROR: Input school name should only be at most 60 characters long";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullSchoolName;

    /**
     * Constructs a {@code School}.
     *
     * @param schoolName A valid school.
     */
    public School(String schoolName) {
        requireNonNull(schoolName);
        checkArgument(isValidLength(schoolName), INVALID_LENGTH_MESSAGE);
        checkArgument(isValidSchool(schoolName), MESSAGE_CONSTRAINTS);
        fullSchoolName = schoolName;
    }

    /**
     * Returns true if a given string is within the characters limit
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MAX_CHARACTERS;
    }


    /**
     * Returns true if a given string is a valid school.
     */
    public static boolean isValidSchool(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullSchoolName;
    }

    @Override
    public int compareTo(School other) {
        String thisSchool = this.fullSchoolName.toLowerCase(Locale.ROOT);
        String otherSchool = other.fullSchoolName.toLowerCase(Locale.ROOT);
        return thisSchool.compareTo(otherSchool);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof School // instanceof handles nulls
                && fullSchoolName.equals(((School) other).fullSchoolName)); // state check
    }

    @Override
    public int hashCode() {
        return fullSchoolName.hashCode();
    }

}
