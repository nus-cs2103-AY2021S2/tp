package seedu.address.model.session;

import static java.util.Objects.requireNonNull;

/**
 * Represents the subject that is taught for that session.
 * Up to user's discretion to specify the level of detail of the subject.
 */
public class Subject {

    private String value;

    /**
     * Constructs a {@code Subject}.
     *
     * @param value The subject that is being taught for that session
     */
    public Subject(String value) {
        requireNonNull(value);
        this.value = value;
    }
}
