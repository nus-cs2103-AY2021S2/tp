package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.tutor.Gender;

public class GenderFilter implements Predicate<Gender> {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender filters should only contain alphanumeric characters and spaces,"
            + " and it should not be blank";

    /*
     * The first character of the gender filter must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String genderFilter;

    /**
     * Constructs a {@code GenderFilter}.
     *
     * @param genderFilter A valid gender filter.
     */
    public GenderFilter(String genderFilter) {
        requireNonNull(genderFilter);
        checkArgument(isValidGenderFilter(genderFilter), MESSAGE_CONSTRAINTS);
        this.genderFilter = genderFilter.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid gender filter.
     */
    public static boolean isValidGenderFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Gender: " + genderFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderFilter // instanceof handles nulls
                && genderFilter.equals(((GenderFilter) other).genderFilter)); // state check
    }

    @Override
    public int hashCode() {
        return genderFilter.hashCode();
    }

    @Override
    public boolean test(Gender gender) {
        if (gender == null) {
            return false;
        }

        return gender.personGender.toLowerCase().contains(genderFilter);
    }
}
