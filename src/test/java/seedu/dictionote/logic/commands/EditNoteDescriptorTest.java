package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.DESC_NOTE;
import static seedu.dictionote.logic.commands.EditNoteCommand.EditNoteDescriptor;

import org.junit.jupiter.api.Test;

public class EditNoteDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditNoteDescriptor descriptorWithSameValues = new EditNoteDescriptor(DESC_NOTE);
        assertTrue(DESC_NOTE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_NOTE.equals(DESC_NOTE));

        // null -> returns false
        assertFalse(DESC_NOTE.equals(null));

        // different types -> returns false
        assertFalse(DESC_NOTE.equals(100));

    }
}
