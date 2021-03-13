package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Meeting's Description in the meeting book.
 * Guarantees: immutable; should always be valid.}
 */
public class Description {

    public final String fullDescription;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid Description.
     */
    public Description(String description) {
        requireNonNull(description);
        fullDescription = description;
    }



    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.meeting.Description // instanceof handles nulls
                && fullDescription.equals(((seedu.address.model.meeting.Description) other)
                                .fullDescription)); // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
