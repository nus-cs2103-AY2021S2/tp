package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.subject.SubjectExperience;

public class SubjectExperienceFilter implements Predicate<SubjectExperience> {
    public static final String MESSAGE_CONSTRAINTS =
            "SubjectExperienceFilter should contain numbers, and it should be at least 1 digit long."
            + " Optionally, the inequalities >, <, >=, <=, = can be used";

    public static final String VALIDATION_REGEX = "^[><]?=?\\d{1,}";

    public final String subjectExperienceFilter;
    public final String subjectExperienceFilterInequality;
    public final Integer subjectExperienceFilterNumber;

    /**
     * Constructs a {@code SubjectExperienceFilter}.
     *
     * @param subjectExperienceFilter A valid subject experience filter.
     */
    public SubjectExperienceFilter(String subjectExperienceFilter) {
        requireNonNull(subjectExperienceFilter);
        checkArgument(isValidSubjectExperienceFilter(subjectExperienceFilter), MESSAGE_CONSTRAINTS);

        this.subjectExperienceFilter = subjectExperienceFilter;
        subjectExperienceFilterInequality = subjectExperienceFilter.replaceAll("\\d{1,}", "");
        subjectExperienceFilterNumber = Integer.parseInt(subjectExperienceFilter.replaceAll("^[><]?=?", ""));
    }

    /**
     * Returns true if a given string is a valid subject experience filter.
     */
    public static boolean isValidSubjectExperienceFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Subject Experience: " + subjectExperienceFilterInequality + " "
                + subjectExperienceFilterNumber.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectExperienceFilter // instanceof handles nulls
                && subjectExperienceFilter.equals((
                        (SubjectExperienceFilter) other).subjectExperienceFilter)); // state check
    }

    @Override
    public int hashCode() {
        return subjectExperienceFilter.hashCode();
    }

    @Override
    public boolean test(SubjectExperience subjectExperience) {
        if (subjectExperience == null) {
            return false;
        }

        boolean isFiltered = false;

        isFiltered = isFiltered || subjectExperienceFilterInequality.isBlank()
                && subjectExperience.experience.equals(subjectExperienceFilterNumber);

        isFiltered = isFiltered || subjectExperienceFilterInequality.contains("=")
                && subjectExperience.experience.equals(subjectExperienceFilterNumber);

        isFiltered = isFiltered || subjectExperienceFilterInequality.contains(">")
                && subjectExperience.experience > subjectExperienceFilterNumber;

        isFiltered = isFiltered || subjectExperienceFilterInequality.contains("<")
                && subjectExperience.experience < subjectExperienceFilterNumber;

        return isFiltered;
    }
}
