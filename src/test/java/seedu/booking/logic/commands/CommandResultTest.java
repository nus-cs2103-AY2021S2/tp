package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_NONE;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_PREVIOUS;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, COMMAND_SHOW_NONE, false)));
        assertTrue(commandResult.equals(new CommandResult("feedback", COMMAND_SHOW_NONE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, COMMAND_SHOW_NONE, false)));

        // different show type value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, COMMAND_SHOW_PREVIOUS, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, COMMAND_SHOW_NONE, true)));

    }

    @Test
    public void isShowHelp() {
        CommandResult commandResult = new CommandResult("feedback");

        // default showHelp -> returns false
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void isExit() {
        CommandResult commandResult = new CommandResult("feedback");

        // default isExit -> returns false
        assertFalse(commandResult.isExit());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // default values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", COMMAND_SHOW_NONE).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true,
                COMMAND_SHOW_NONE, false).hashCode());

        // different showType value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                COMMAND_SHOW_PREVIOUS, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                COMMAND_SHOW_NONE, true).hashCode());
    }
}
