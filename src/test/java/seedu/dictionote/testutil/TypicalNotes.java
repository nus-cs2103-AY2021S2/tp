package seedu.dictionote.testutil;

import seedu.dictionote.model.AddressBook;
import seedu.dictionote.model.NoteBook;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalNotes {

    public static final Note MI_AMOR = new Note("My love, I have been waiting for you");
    public static final Note MI_VIDA = new Note("Life has been great");
    public static final Note CS2103T_EXAM = new Note("CS2103T Exam will be on Monday");

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
