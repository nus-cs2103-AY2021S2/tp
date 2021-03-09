package seedu.dictionote.testutil;

import seedu.dictionote.model.note.Note;

/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {

    public static final String DEFAULT_CONTENT = "This is a CS2103T note";

    private String note;

    /**
     * Creates a {@code NoteBuilder} with the default details.
     */
    public NoteBuilder() {
        note = DEFAULT_CONTENT;
    }

    /**
     * Initializes the NoteBuilder with the data of {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        note = noteToCopy.getNote();
    }

    public Note build() {
        return new Note(note);
    }

}
