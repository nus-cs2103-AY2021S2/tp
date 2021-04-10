package seedu.address.model.shortcut;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_NAME;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ShortcutTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Shortcut(null, null));
    }

    @Test
    public void constructor_invalidShortcutName_throwsIllegalArgumentException() {
        String invalidShortcutName = "";
        String validShortcutCommand = "list";
        assertThrows(IllegalArgumentException.class, () -> new Shortcut(invalidShortcutName, validShortcutCommand));
    }

    @Test
    public void constructor_invalidShortcutCommand_throwsIllegalArgumentException() {
        String validShortcutName = "abc";
        String invalidShortcutCommand = "sort";
        assertThrows(IllegalArgumentException.class, () -> new Shortcut(validShortcutName, invalidShortcutCommand));
    }

    @Test
    public void isValidShortcutName() {
        // null shortcut name
        assertThrows(NullPointerException.class, () -> Shortcut.isValidShortcutName(null));
    }

    @Test
    public void isValidShortcutCommand() {
        // null shortcut command
        assertThrows(NullPointerException.class, () -> Shortcut.isValidShortcutCommand(null));
    }

    @Test
    public void getShortcutName() {
        Shortcut shortcut = new Shortcut(VALID_SHORTCUT_NAME, VALID_SHORTCUT_COMMAND);
        assertTrue(shortcut.getShortcutName().equals(VALID_SHORTCUT_NAME));
    }

    @Test
    public void getShortcutCommand() {
        Shortcut shortcut = new Shortcut(VALID_SHORTCUT_NAME, VALID_SHORTCUT_COMMAND);
        assertTrue(shortcut.getShortcutCommand().equals(VALID_SHORTCUT_COMMAND));
    }

    @Test
    public void isSameShortcut() {
        Shortcut first = new Shortcut("ls", "list");
        Shortcut second = new Shortcut("ls", "help");
        Shortcut third = new Shortcut("sna", "list");

        // same name, different command -> true
        assertTrue(first.isSameShortcutName(second));

        // same command, different name -> false
        assertFalse(first.isSameShortcutName(third));

        // same values, different object -> true
        assertTrue(first.isSameShortcutName(new Shortcut("ls", "list")));

        // null -> false
        assertFalse(first.isSameShortcutName(null));

    }
}
