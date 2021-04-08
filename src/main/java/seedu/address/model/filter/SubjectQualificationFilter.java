package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.subject.SubjectQualification;

public class SubjectQualificationFilter implements Predicate<SubjectQualification> {
    public static final String MESSAGE_CONSTRAINTS =
            "SubjectQualification filters should only contain alphanumeric characters, spaces,"
            + " the symbols - ( ) and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\-)(][\\p{Alnum} \\-)(]*";

    public final String subjectQualificationFilter;

    /**
     * Constructs a {@code SubjectQualificationFilter}.
     *
     * @param subjectQualificationFilter A valid subject qualification filter.
     */
    public SubjectQualificationFilter(String subjectQualificationFilter) {
        requireNonNull(subjectQualificationFilter);
        checkArgument(isValidSubjectQualificationFilter(subjectQualificationFilter), MESSAGE_CONSTRAINTS);
        this.subjectQualificationFilter = subjectQualificationFilter.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid subject qualification filter.
     */
    public static boolean isValidSubjectQualificationFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Subject Qualification: " + subjectQualificationFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectQualificationFilter // instanceof handles nulls
                && subjectQualificationFilter.equals((
                        (SubjectQualificationFilter) other).subjectQualificationFilter)); // state check
    }

    @Override
    public int hashCode() {
        return subjectQualificationFilter.hashCode();
    }

    @Override
    public boolean test(SubjectQualification subjectQualification) {
        if (subjectQualification == null) {
            return false;
        }

        return subjectQualification.qualification.toLowerCase().contains(subjectQualificationFilter);
    }
}
