package seedu.dictionote.model.note;

import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Note in the dictionote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Note {
    private final String note;

    /**
     * Every field must be present and not null.
     */
    public Note(String note) {
        requireAllNonNull(note);
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    /**
     * Returns true if both notes have the same note.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }

        return otherNote != null
                && otherNote.getNote().equals(getNote());
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

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return otherNote.getNote().equals(getNote());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(note);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNote());
        return builder.toString();
    }
}
