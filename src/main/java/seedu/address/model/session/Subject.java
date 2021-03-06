package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Subject in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
            "Subjects should only contain alphanumeric characters, spaces, and dashes, and it should not be blank.\n"
            + "Different words should be seperated by only a single space ' ' or dash '-'";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]+([ -][a-zA-Z0-9]+)*$";

    public final String subject;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subject A valid Subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        this.subject = subject.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid Subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return subject;
    }
}
