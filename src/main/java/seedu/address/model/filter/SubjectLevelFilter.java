package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.subject.SubjectLevel;

public class SubjectLevelFilter implements Predicate<SubjectLevel> {
    public static final String MESSAGE_CONSTRAINTS =
            "SubjectLevel filters should only contain alphanumeric characters and spaces,"
            + " and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String subjectLevelFilter;

    /**
     * Constructs a {@code SubjectLevelFilter}.
     *
     * @param subjectLevelFilter A valid subject level filter.
     */
    public SubjectLevelFilter(String subjectLevelFilter) {
        requireNonNull(subjectLevelFilter);
        checkArgument(isValidSubjectLevelFilter(subjectLevelFilter), MESSAGE_CONSTRAINTS);
        this.subjectLevelFilter = subjectLevelFilter.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid subject level filter.
     */
    public static boolean isValidSubjectLevelFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Subject Level: " + subjectLevelFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectLevelFilter // instanceof handles nulls
                && subjectLevelFilter.equals(((SubjectLevelFilter) other).subjectLevelFilter)); // state check
    }

    @Override
    public int hashCode() {
        return subjectLevelFilter.hashCode();
    }

    @Override
    public boolean test(SubjectLevel subjectLevel) {
        if (subjectLevel == null) {
            return false;
        }

        return subjectLevel.level.toLowerCase().contains(subjectLevelFilter);
    }
}
