package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;
/**
 * Represents a Subject in TutorsPet.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSubjectName(String)}
 */
public class Subject implements Comparable<Subject> {
    public static final String MESSAGE_CONSTRAINTS = "Subject names should be an abbreviation from the list "
            + "[bio, chem, cn, econ, eng, geo, hist, lit, mal, math, phys, sci, tam], which respectively means "
            + "[biology, chemistry, chinese, economics, english, geography, history, literature, malay, mathematics,"
            + " physics, science, tamil]";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String subjectName;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subjectName A valid subject name.
     */
    public Subject(String subjectName) {
        requireNonNull(subjectName);
        checkArgument(isValidSubjectName(subjectName.toLowerCase(Locale.ROOT)), MESSAGE_CONSTRAINTS);
        this.subjectName = subjectName;
    }

    /**
     * Returns true if a given string is a valid subject name.
     */
    public static boolean isValidSubjectName(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            for (AvailableSubject subject : AvailableSubject.values()) {
                if (subject.name().equals(test)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int compareTo(Subject other) {
        return this.subjectName.toLowerCase(Locale.ROOT).compareTo(other.subjectName.toLowerCase(Locale.ROOT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && subjectName.equals(((Subject) other).subjectName)); // state check
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + subjectName + ']';
    }

}
