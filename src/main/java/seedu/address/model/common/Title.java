package seedu.address.model.common;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Filterable;

/**
 * Represents a {@code String} Title variable.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title implements Filterable {
    public static final String MESSAGE_CONSTRAINTS =
            "Title should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Title}.
     *
     * @param title A valid title.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        value = title;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && value.equalsIgnoreCase(((Title) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean filter(String s) {
        return value.contains(s);
    }
}
