package seedu.dictionote.model.dictionary;

import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Note in the dictionote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Content {
    private final String content;

    /**
     * Every field must be present and not null.
     */
    public Content(String content) {
        requireAllNonNull(content);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    /**
     * Returns true if both notes have the same note.
     */
    public boolean isSameContent(Content otherContent) {
        if (otherContent == this) {
            return true;
        }

        return otherContent != null
                && otherContent.getContent().equals(getContent());
    }

    /**
     * Returns true if both notes have the same identity and data fields.
     * This defines a stronger notion of equality between two notes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Content)) {
            return false;
        }

        Content otherContent = (Content) other;
        return otherContent.getContent().equals(getContent());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getContent());
        return builder.toString();
    }
}
