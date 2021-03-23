package seedu.dictionote.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.note.UniqueNoteList;

/**
 * Wraps all data at the notebook level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class NoteBook implements ReadOnlyNoteBook {
    private final UniqueNoteList notes;

    {
        notes = new UniqueNoteList();
    }

    public NoteBook() {}

    /** Make a notebook
     *
     * @param toBeCopied ReadOnlyNotebook
     */

    public NoteBook(ReadOnlyNoteBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the note list with {@code notes}.
     * {@code notes} must not contain duplicate notes.
     */
    public void setNotes(List<Note> notes) {
        this.notes.setNotes(notes);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyNoteBook newData) {
        requireNonNull(newData);
        setNotes(newData.getNoteList());
    }

    //// note-level operations

    /**
     * Returns true if a note with the same content as {@code note} exists in the dictionote book.
     */
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return notes.contains(note);
    }

    /**
     * Adds a note to the dictionote book.
     * The note must not already exist in the dictionote book.
     */
    public void addNote(Note n) {
        notes.add(n);
    }

    public void deleteNote(Note n) {
        notes.delete(n);
    }

    public void setNote(Note target, Note editedNote) {
        requireNonNull(editedNote);
        notes.setNote(target, editedNote);
    }

    //// util methods

    @Override
    public String toString() {
        return notes.asUnmodifiableObservableList().size() + " notes";
        // TODO: refine later
    }

    @Override
    public ObservableList<Note> getNoteList() {
        return notes.asUnmodifiableObservableList();
    }

    public void sortNote() {
        notes.sort();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteBook // instanceof handles nulls
                && notes.equals(((NoteBook) other).notes));
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }

}
