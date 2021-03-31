package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

import seedu.address.model.meeting.Priority;

public class Note {
    private final String note;
    private final Priority priority;

    public Note(String note) {
        requireNonNull(note);
        this.note = note;
        this.priority = new Priority("1");
    }

    public Note(String note, Priority priority) {
        this.note = note;
        this.priority = priority;
    }

    /**
     * Returns true if both notes have the same note (name).
     * This defines a weaker notion of equality between two notes.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }

        return otherNote != null
                && otherNote.getNote().equals(getNote());
    }

    @Override
    public int hashCode() {
        return this.note.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && this.note.equals(((Note) other).note)); // state check
    }

    public String getNote() {
        return this.note;
    }
}
