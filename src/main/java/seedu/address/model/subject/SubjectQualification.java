package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Tutor's qualifications in a subject in Tutor Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidQualification(String)}
 */
public class SubjectQualification {
    public static final String MESSAGE_CONSTRAINTS =
            "Qualifications should only contain alphanumeric characters, spaces,"
            + " the symbols - ( ), it should be between 1 to 20 characters and it should not be blank";

    /*
     * The first character of the subject qualification must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(?=^.{0,20}$)[\\p{Alnum}\\-)(][\\p{Alnum} \\-)(]*";

    public final String qualification;

    /**
     * Constructs a {@code SubjectQualification}.
     *
     * @param qualification A valid subject qualification.
     */
    public SubjectQualification(String qualification) {
        requireNonNull(qualification);
        checkArgument(isValidQualification(qualification), MESSAGE_CONSTRAINTS);
        this.qualification = qualification;
    }

    /**
     * Returns true if a given string is a valid subject qualification.
     */
    public static boolean isValidQualification(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return qualification;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectQualification // instanceof handles nulls
                && qualification.equals(((SubjectQualification) other).qualification)); // state check
    }

    @Override
    public int hashCode() {
        return qualification.hashCode();
    }
}
