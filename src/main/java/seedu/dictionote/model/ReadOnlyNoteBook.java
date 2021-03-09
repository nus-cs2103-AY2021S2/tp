package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.note.Note;

public interface ReadOnlyNoteBook {
    /**
     * Returns an unmodifiable view of the notes list.
     * This list will not contain any duplicate notes.
     */
    ObservableList<Note> getNoteList();
}
