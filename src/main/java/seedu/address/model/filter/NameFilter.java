package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.tutor.Name;

public class NameFilter implements Predicate<Name> {
    public static final String MESSAGE_CONSTRAINTS =
            "Name filters should only contain alphanumeric characters and spaces,"
            + " and it should not be blank";

    /*
     * The first character of the name filter must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String nameFilter;

    /**
     * Constructs a {@code NameFilter}.
     *
     * @param nameFilter A valid name filter.
     */
    public NameFilter(String nameFilter) {
        requireNonNull(nameFilter);
        checkArgument(isValidNameFilter(nameFilter), MESSAGE_CONSTRAINTS);
        this.nameFilter = nameFilter.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid name filter.
     */
    public static boolean isValidNameFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Name: " + nameFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameFilter // instanceof handles nulls
                && nameFilter.equals(((NameFilter) other).nameFilter)); // state check
    }

    @Override
    public int hashCode() {
        return nameFilter.hashCode();
    }

    @Override
    public boolean test(Name name) {
        if (name == null) {
            return false;
        }

        return name.fullName.toLowerCase().contains(nameFilter);
    }
}
