package seedu.dictionote.model.note;

import static java.time.LocalDateTime.now;
import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.dictionote.model.tag.Tag;

/**
 * Represents a Note in the dictionote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Note {
    private final String note;
    // Data fields
    private Set<Tag> tags = new HashSet<>();
    private LocalDateTime createTime;
    private LocalDateTime lastEditTime;
    private Boolean isDone;

    /**
     * Every field must be present and not null.
     */

    public Note(String note) {
        requireAllNonNull(note, tags);
        this.note = note;
        this.tags = new HashSet<>();
        this.createTime = now();
        this.lastEditTime = now();
        this.isDone = false;
    }

    /**
     * Constructor with the note and tag
     */

    public Note(String note, Set<Tag> tags) {
        requireAllNonNull(note, tags);
        this.note = note;
        this.tags.addAll(tags);
        this.createTime = now();
        this.lastEditTime = now();
        this.isDone = false;
    }

    private Note(String note, Set<Tag> tags, LocalDateTime createdTime, Boolean isDone) {
        requireAllNonNull(note, tags);
        this.note = note;
        this.tags.addAll(tags);
        this.createTime = createdTime;
        this.lastEditTime = now();
        this.isDone = isDone;
    }

    /**
     * Constructor with the note and tag
     */

    public Note(String note, Set<Tag> tags, LocalDateTime createdTime,
                 LocalDateTime lastEditTime, Boolean isDone) {
        requireAllNonNull(note, tags);
        this.note = note;
        this.tags.addAll(tags);
        this.createTime = createdTime;
        this.lastEditTime = lastEditTime;
        this.isDone = isDone;
    }
    /**
     * Method to call the above private constructor for note.
     */

    public Note createEditedNote(String note, Set<Tag> tags, LocalDateTime createdTime,
                                 Boolean isDone) {
        return new Note(note, tags, createdTime, isDone);
    }

    public Note createEditedNote(String note, Set<Tag> tags, LocalDateTime createdTime,
                                 LocalDateTime lastEditTime, Boolean isDone) {
        return new Note(note, tags, createdTime, lastEditTime, isDone);
    }

    /**
     * Method to call the above private constructor for note.
     */

    public Note markAsDoneNote(String note, Set<Tag> tags, LocalDateTime createdTime) {
        return new Note(note, tags, createdTime, true);
    }

    /**
     * Method to call the above private constructor for note.
     */

    public Note markAsUndoneNote(String note, Set<Tag> tags, LocalDateTime createdTime) {
        return new Note(note, tags, createdTime, false);
    }

    public String getNote() {
        return note;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setLastEditTime(LocalDateTime time) {
        this.lastEditTime = time;
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
        return otherNote.getNote().equals(getNote())
                && otherNote.getTags().equals(getTags())
                && otherNote.isDone().equals(isDone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(note, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNote());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("; Time Created: ");
        builder.append(this.createTime.toString());
        builder.append("; Last Edited: ");
        builder.append(this.lastEditTime.toString());

        return builder.toString();
    }
}
