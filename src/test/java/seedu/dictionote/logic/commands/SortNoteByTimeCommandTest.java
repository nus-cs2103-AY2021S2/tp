package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortNoteByTimeCommandTest {
    @Test
    public void equals() {
        SortNoteByTimeCommand sortNoteByTimeCommand = new SortNoteByTimeCommand();
        SortNoteByTimeCommand sortNoteByTimeOtherCommand = new SortNoteByTimeCommand();

        // same object -> returns true
        assertTrue(sortNoteByTimeCommand.equals(sortNoteByTimeCommand));

        // different types -> returns false
        assertFalse(sortNoteByTimeCommand.equals("tree"));

        // null -> returns false
        assertFalse(sortNoteByTimeCommand.equals(null));

        // different object return false
        assertFalse(sortNoteByTimeCommand.equals(sortNoteByTimeOtherCommand));
    }
}
