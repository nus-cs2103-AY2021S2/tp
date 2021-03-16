package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the subject that is taught for that session.
 * Up to user's discretion to specify the level of detail of the subject.
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS = "Subject cannot be empty.";

    private String value;

    /**
     * Constructs a {@code Subject}.
     *
     * @param value The subject that is being taught for that session
     */
    public Subject(String value) {
        requireNonNull(value);
        checkArgument(isValidSubject(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Returns true if subject is non empty.
     */
    public static boolean isValidSubject(String value) {
        return !value.equals("");
    }

    @Override
    public String toString() {
        return value;
    }
}
