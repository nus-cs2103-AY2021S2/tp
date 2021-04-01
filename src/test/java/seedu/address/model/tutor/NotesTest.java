package seedu.address.model.tutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NotesTest {

    @Test
    void isEmpty_true() {
        Notes notes = new Notes("");
        assertTrue(notes.isEmpty());
    }

    @Test
    void isEmpty_false() {
        Notes notes = new Notes("some notes here");
        assertFalse(notes.isEmpty());
    }

    @Test
    void testToString() {
        String notesString = "some notes here";
        Notes notes = new Notes(notesString);

        assertEquals(notesString, notes.toString());
    }

    @Test
    void filter_true() {
        String notesString = "i like avocados";
        Notes notes = new Notes(notesString);

        assertTrue(notes.filter("avocados"));
    }

    @Test
    void filter_false() {
        String notesString = "i like avocados";
        Notes notes = new Notes(notesString);

        assertFalse(notes.filter("banana"));
    }
}

