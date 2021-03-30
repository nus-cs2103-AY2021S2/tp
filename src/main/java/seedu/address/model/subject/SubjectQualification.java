package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Filterable;

/**
 * Represents a Tutor's qualifications in a subject in Tutor Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidQualification(String)}
 */
public class SubjectQualification implements Filterable {
    public static final String MESSAGE_CONSTRAINTS =
            "Qualifications should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the subject qualification must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum} \\-)(]{2,20}$";

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

    @Override
    public boolean filter(String s) {
        return qualification.contains(s);
    }
}
