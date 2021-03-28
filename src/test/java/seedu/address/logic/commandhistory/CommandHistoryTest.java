package seedu.address.logic.commandhistory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    private final CommandHistory commandHistory = CommandHistory.getCommandHistory();

    @BeforeEach
    public void setUp() {
        commandHistory.clearCommandHistory();
    }

    @Test
    public void addCommand_success() {
        // valid command
        assertDoesNotThrow(() -> commandHistory.addCommand("Test"));
    }

    @Test
    public void addCommand_null_throwsNullPointerException() {
        // null command
        assertThrows(NullPointerException.class, () -> commandHistory.addCommand(null));
    }

    @Test
    public void previousAndNextCommand_success() {
        String currCommand = "CurrentCommand";
        String commandInHistory1 = "TestPrevious1";
        String commandInHistory2 = "TestPrevious2";

        // User presses up key, no history, return current command.
        assertEquals(currCommand, commandHistory.getPrevious(currCommand));

        // Add 2 commands to history.
        commandHistory.addCommand(commandInHistory1);
        commandHistory.addCommand(commandInHistory2);

        // User presses up key, return second command in history.
        assertEquals(commandInHistory2, commandHistory.getPrevious(currCommand));

        // User presses up again, return first command in history.
        assertEquals(commandInHistory1, commandHistory.getPrevious(commandInHistory2));

        // User presses up again, return first command in history.
        assertEquals(commandInHistory1, commandHistory.getPrevious(commandInHistory1));

        // User presses down, return second command in history.
        assertEquals(commandInHistory2, commandHistory.getNext(commandInHistory1));

        // User presses down, return current command.
        assertEquals(currCommand, commandHistory.getNext(commandInHistory2));

        // User presses down, return current command.
        assertEquals(currCommand, commandHistory.getNext(currCommand));
    }
}
