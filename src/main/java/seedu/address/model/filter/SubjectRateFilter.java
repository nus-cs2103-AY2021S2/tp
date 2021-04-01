package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.subject.SubjectRate;

public class SubjectRateFilter implements Predicate<SubjectRate> {
    public static final String MESSAGE_CONSTRAINTS =
            "SubjectRateFilter should contain numbers, and it should be at least 1 digit long."
            + " Optionally, the inequalities >, <, >=, <=, = can be used";

    public static final String VALIDATION_REGEX = "^[><]?=?\\d{1,}";

    public final String subjectRateFilter;
    public final String subjectRateFilterInequality;
    public final Integer subjectRateFilterNumber;

    /**
     * Constructs a {@code SubjectRateFilter}.
     *
     * @param subjectRateFilter A valid subject rate filter.
     */
    public SubjectRateFilter(String subjectRateFilter) {
        requireNonNull(subjectRateFilter);
        checkArgument(isValidSubjectRateFilter(subjectRateFilter), MESSAGE_CONSTRAINTS);

        this.subjectRateFilter = subjectRateFilter;
        subjectRateFilterInequality = subjectRateFilter.replaceAll("\\d{1,}", "");
        subjectRateFilterNumber = Integer.parseInt(subjectRateFilter.replaceAll("^[><]?=?", ""));
    }

    /**
     * Returns true if a given string is a valid subject rate filter.
     */
    public static boolean isValidSubjectRateFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Subject Rate: " + subjectRateFilterInequality + " " + subjectRateFilterNumber.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectRateFilter // instanceof handles nulls
                && subjectRateFilter.equals(((SubjectRateFilter) other).subjectRateFilter)); // state check
    }

    @Override
    public int hashCode() {
        return subjectRateFilter.hashCode();
    }

    @Override
    public boolean test(SubjectRate subjectRate) {
        if (subjectRate == null) {
            return false;
        }

        boolean isFiltered = false;

        isFiltered = isFiltered || subjectRateFilterInequality.isBlank()
                && subjectRate.rate.equals(subjectRateFilterNumber);

        isFiltered = isFiltered || subjectRateFilterInequality.contains("=")
                && subjectRate.rate.equals(subjectRateFilterNumber);

        isFiltered = isFiltered || subjectRateFilterInequality.contains(">")
                && subjectRate.rate > subjectRateFilterNumber;

        isFiltered = isFiltered || subjectRateFilterInequality.contains("<")
                && subjectRate.rate < subjectRateFilterNumber;

        return isFiltered;
    }
}
