package seedu.address.testutil;

import seedu.address.model.meeting.Priority;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;

/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {

    public static final String DEFAULT_CONTENT = "Complete CS2103 Tutorial";
    public static final String DEFAULT_PRIORITY = "1";

    private Content content;
    private Priority priority;

    /**
     * Creates a {@code NoteBuilder} with the default details.
     */
    public NoteBuilder() {
        content = new Content(DEFAULT_CONTENT);
        priority = new Priority(DEFAULT_PRIORITY);
    }

    /**
     * Initializes the NoteBuilder with the data of {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        content = noteToCopy.getContent();
        priority = noteToCopy.getPriority();
    }

    /**
     * Sets the {@code Content} of the {@code Note} that we are building.
     */
    public NoteBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Note} that we are building.
     */
    public NoteBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    public Note build() {
        return new Note(content, priority);
    }

}
