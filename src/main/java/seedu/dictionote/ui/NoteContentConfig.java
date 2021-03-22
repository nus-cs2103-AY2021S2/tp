package seedu.dictionote.ui;

import seedu.dictionote.model.note.Note;

public interface NoteContentConfig {
    /**
     * set and show the note.
     */
    void setNote(Note note);

    /**
     * Check if there is note show.
     */
    boolean haveNote();

    /**
     * Reset content of the displayed note.
     */
    void resetNote();

    /**
     * Get the current content on the textarea.
     */
    String getEditedContent();

    /**
     * Get the note shown on display.
     */
    Note getNote();
}
