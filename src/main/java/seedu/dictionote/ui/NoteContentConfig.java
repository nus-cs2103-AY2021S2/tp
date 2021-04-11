package seedu.dictionote.ui;

import seedu.dictionote.model.note.Note;


/**
 * NoteContentConfig is used as a access point for others component to request for its UI changes.
 */
public interface NoteContentConfig {
    /**
     * sets and show the note.
     */
    void setNote(Note note);

    /**
     * Checks if there is note show.
     */
    boolean haveNote();

    /**
     * Resets content of the displayed note.
     */
    void resetNote();

    /**
     * Checks if the UI is current on edit mode.
     */
    boolean onEditMode();

    /**
     * Gest the current content on the textarea.
     */
    String getEditedContent();

    /**
     * Gets the note shown on display.
     */
    Note getNote();
}
