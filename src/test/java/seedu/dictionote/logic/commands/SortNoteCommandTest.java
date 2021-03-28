package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortNoteCommandTest {
    @Test
    public void equals() {
        SortNoteCommand sortNoteCommand = new SortNoteCommand();
        SortNoteCommand sortNoteOtherCommand = new SortNoteCommand();

        // same object -> returns true
        assertTrue(sortNoteCommand.equals(sortNoteCommand));

        // different types -> returns false
        assertFalse(sortNoteCommand.equals(22022001));

        // null -> returns false
        assertFalse(sortNoteCommand.equals(null));

        // different contact -> returns true
        assertTrue(sortNoteCommand.equals(sortNoteCommand));
    }
}
