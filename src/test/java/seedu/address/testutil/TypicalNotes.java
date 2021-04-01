package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.note.Note;
import seedu.address.model.note.NoteBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;

public class TypicalNotes {

    public static final Note NOTE1 = new NoteBuilder().withContent("Complete CS2103 Tutorial")
            .withPriority("5").build();
    public static final Note NOTE2 = new NoteBuilder().withContent("CS2106 Lab")
            .withPriority("5").build();
    public static final Note NOTE3 = new NoteBuilder().withContent("Lunch with JC friends")
            .withPriority("4").build();
    public static final Note NOTE4 = new NoteBuilder().withContent("Play badminton")
            .withPriority("3").build();
    public static final Note NOTE5 = new NoteBuilder().withContent("Play table tennis")
            .withPriority("3").build();
    public static final Note NOTE6 = new NoteBuilder().withContent("Borrow books")
            .withPriority("2").build();
    public static final Note NOTE7 = new NoteBuilder().withContent("Hiking")
            .withPriority("1").build();


    private TypicalNotes() {} // prevents instantiation

    /**
     * Returns an {@code NoteBook} with all the typical persons.
     */
    public static NoteBook getTypicalNoteBook() {
        NoteBook nb = new NoteBook();
        for (Note note : getTypicalNotes()) {
            nb.addNote(note);
        }
        return nb;
    }

    public static List<Note> getTypicalNotes() {
        return new ArrayList<>(Arrays.asList(NOTE1, NOTE2, NOTE3, NOTE4, NOTE5, NOTE6, NOTE7));
    }
}
