package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.subject.SubjectName;

public class SubjectNameFilter implements Predicate<SubjectName> {
    public static final String MESSAGE_CONSTRAINTS =
            "SubjectName filters should only contain alphanumeric characters and spaces,"
            + " and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String subjectNameFilter;

    /**
     * Constructs a {@code SubjectNameFilter}.
     *
     * @param subjectNameFilter A valid subject name filter.
     */
    public SubjectNameFilter(String subjectNameFilter) {
        requireNonNull(subjectNameFilter);
        checkArgument(isValidSubjectNameFilter(subjectNameFilter), MESSAGE_CONSTRAINTS);
        this.subjectNameFilter = subjectNameFilter.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid subject name filter.
     */
    public static boolean isValidSubjectNameFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Subject Name: " + subjectNameFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectNameFilter // instanceof handles nulls
                && subjectNameFilter.equals(((SubjectNameFilter) other).subjectNameFilter)); // state check
    }

    @Override
    public int hashCode() {
        return subjectNameFilter.hashCode();
    }

    @Override
    public boolean test(SubjectName subjectName) {
        if (subjectName == null) {
            return false;
        }

        return subjectName.name.toLowerCase().contains(subjectNameFilter);
    }
}
