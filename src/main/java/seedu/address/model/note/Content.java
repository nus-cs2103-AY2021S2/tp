package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Note's content in the note book.
 * Guarantees: immutable; is valid.
 */
public class Content {

    public final String content;

    /**
     * Constructs a {@code Content}.
     *
     * @param content A valid Content.
     */
    public Content(String content) {
        requireNonNull(content);
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.note.Content // instanceof handles nulls
                && content.equalsIgnoreCase(((Content) other).content)); // state check
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

}

