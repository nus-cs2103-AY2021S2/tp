package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Filterable;

/**
 * Represents a Subject's education level in Tutor Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidLevel(String)}
 */
public class SubjectLevel implements Filterable {
    public static final String MESSAGE_CONSTRAINTS =
            "Subject level should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the subject level must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String level;

    /**
     * Constructs a {@code SubjectLevel}.
     *
     * @param level A valid subject level.
     */
    public SubjectLevel(String level) {
        requireNonNull(level);
        checkArgument(isValidLevel(level), MESSAGE_CONSTRAINTS);
        this.level = level;
    }

    /**
     * Returns true if a given string is a valid subject level.
     */
    public static boolean isValidLevel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return level;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectLevel // instanceof handles nulls
                && level.equals(((SubjectLevel) other).level)); // state check
    }

    @Override
    public int hashCode() {
        return level.hashCode();
    }

    @Override
    public boolean filter(String s) {
        return level.contains(s);
    }
}
