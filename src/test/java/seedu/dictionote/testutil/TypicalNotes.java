package seedu.dictionote.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.dictionote.model.NoteBook;
import seedu.dictionote.model.note.Note;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalNotes {

    public static final Note MI_AMOR = new Note("My love, I have been waiting for you", new HashSet<>());
    public static final Note MI_VIDA = new Note("Life has been great", new HashSet<>());
    public static final Note CS2103T_EXAM = new Note("CS2103T Exam will be on Monday", new HashSet<>());

    private TypicalNotes() {} // prevents instantiation

    /**
     * Returns an {@code NoteBook} with all the typical notes.
     */
    public static NoteBook getTypicalNoteBook() {
        NoteBook nb = new NoteBook();
        for (Note note : getTypicalNotes()) {
            nb.addNote(note);
        }
        return nb;
    }

    public static List<Note> getTypicalNotes() {
        return new ArrayList<>(Arrays.asList(MI_AMOR, MI_VIDA, CS2103T_EXAM));
    }
}
