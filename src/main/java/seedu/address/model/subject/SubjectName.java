package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Filterable;

/**
 * Represents a Subject's name in Tutor Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class SubjectName implements Filterable {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the subject name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code SubjectName}.
     *
     * @param name A valid subject name.
     */
    public SubjectName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectName // instanceof handles nulls
                && name.toLowerCase().equals(((SubjectName) other).name.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean filter(String s) {
        return name.contains(s);
    }
}
