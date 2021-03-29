package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult firstCommandResult = new CommandResult("feedback");
        CommandResult secondCommandResult = new CommandResult("feedback", true, false);

        // same values -> returns same hashcode
        assertEquals(firstCommandResult.hashCode(), new CommandResult("feedback").hashCode());
        assertEquals(secondCommandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(firstCommandResult.hashCode(), new CommandResult("different").hashCode());
        assertNotEquals(secondCommandResult.hashCode(), new CommandResult("different", true, false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(firstCommandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());
        assertNotEquals(secondCommandResult.hashCode(), new CommandResult("feedback", false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(firstCommandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
        assertNotEquals(secondCommandResult.hashCode(), new CommandResult("feedback", true, true).hashCode());
    }
}
