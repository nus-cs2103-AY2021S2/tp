package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ExitUiCommand;
import seedu.address.logic.uicommands.ShowHelpUiCommand;

public class CommandResultTest {
    @Test
    public void hasUiCommand_commandPresent_assertTrue() {
        CommandResult commandResultHelp = new CommandResult("test", new ShowHelpUiCommand());
        CommandResult commandResultExit = new CommandResult("test", new ExitUiCommand());

        assertTrue(commandResultHelp.hasUiCommand());
        assertTrue(commandResultExit.hasUiCommand());
    }

    @Test
    public void hasUiCommand_commandNotPresent_assertTrue() {
        CommandResult commandResultNone = new CommandResult("test");

        assertFalse(commandResultNone.hasUiCommand());
    }

    @Test
    public void constructor_nullValues_throwsException() {
        assertThrows(NullPointerException.class, () -> new CommandResult(null, null));
        assertThrows(NullPointerException.class, () -> new CommandResult("test", null));
        assertThrows(NullPointerException.class, () -> new CommandResult(null));
    }

    @Test
    public void constructor_noNullValues_success() {
        assertDoesNotThrow(() -> new CommandResult("test"));
        assertDoesNotThrow(() -> new CommandResult("test", new ExitUiCommand()));
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", new ShowHelpUiCommand())));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", new ExitUiCommand())));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                new ShowHelpUiCommand()).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                new ExitUiCommand()).hashCode());
    }
}
