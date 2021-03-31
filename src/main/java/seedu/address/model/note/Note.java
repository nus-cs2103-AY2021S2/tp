package seedu.address.model.note;

import static java.util.Objects.requireNonNull;

import seedu.address.model.meeting.Priority;

public class Note {
    private final Content content;
    private final Priority priority;

    public Note(Content content) {
        requireNonNull(content);
        this.content = content;
        this.priority = new Priority("1");
    }

    public Note(Content content, Priority priority) {
        this.content = content;
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
                && otherNote.getContent().equals(getContent());
    }

    @Override
    public int hashCode() {
        return this.content.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && this.content.equals(((Note) other).content)); // state check
    }

    public Content getContent() {
        return this.content;
    }

    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getContent())
                .append("; Priority: ")
                .append(getPriority());

        return builder.toString();
    }
}
