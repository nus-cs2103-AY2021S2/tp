package seedu.dictionote.testutil;

import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_REPLACED_NOTE_CONTENT;

import java.util.HashSet;

import seedu.dictionote.model.note.Note;


/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {

    public static final String DEFAULT_CONTENT = VALID_REPLACED_NOTE_CONTENT;

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

    /**
     * Sets the {@code Note} of the {@code Note} that we are building.
     */
    public NoteBuilder withNote(String name) {
        this.note = name;
        return this;
    }

    public Note build() {
        return new Note(note, new HashSet<>());
    }

}
