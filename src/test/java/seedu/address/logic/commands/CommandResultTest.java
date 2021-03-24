package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.uicommands.UiCommand;

public class CommandResultTest {
    @Test
    public void hasUiCommand_commandPresent_assertTrue() {
        CommandResult commandResultHelp = new CommandResult("test", UiCommand.OPEN_HELP_WINDOW);
        CommandResult commandResultExit = new CommandResult("test", UiCommand.EXIT_APPLICATION);

        assertTrue(commandResultHelp.hasUiCommand());
        assertTrue(commandResultExit.hasUiCommand());
    }

    @Test
    public void hasUiCommand_commandNotPresent_assertTrue() {
        CommandResult commandResultNone = new CommandResult("test", UiCommand.NONE);

        assertFalse(commandResultNone.hasUiCommand());
    }

    @Test
    public void constructor_nullValues_throwsException() {
        assertThrows(NullPointerException.class, () -> new CommandResult(null, null));
        assertThrows(NullPointerException.class, () -> new CommandResult("test", null));
        assertThrows(NullPointerException.class, () -> new CommandResult(null, UiCommand.NONE));
        assertThrows(NullPointerException.class, () ->
                new CommandResult(null, null, null));
    }

    @Test
    public void constructor_indexIsNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new CommandResult("test", UiCommand.NONE, null));
    }

    @Test
    public void constructor_noNullValues_success() {
        assertDoesNotThrow(() -> new CommandResult("test", UiCommand.NONE, Index.fromOneBased(1)));
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", UiCommand.NONE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", UiCommand.OPEN_HELP_WINDOW)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", UiCommand.EXIT_APPLICATION)));
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
                UiCommand.OPEN_HELP_WINDOW).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                UiCommand.EXIT_APPLICATION).hashCode());
    }
}
