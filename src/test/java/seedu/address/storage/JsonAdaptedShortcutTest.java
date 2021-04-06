package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.shortcut.Shortcut;

public class JsonAdaptedShortcutTest {
    private static final String INVALID_NAME_SHORTCUT = "*=list";
    private static final String INVALID_COMMAND_SHORTCUT = "ls=list all";
    private static final String VALID_SHORTCUT = "ls=list";

    private static final Shortcut SHORTCUT = new Shortcut("ls", "list");

    @Test
    public void constructorWithShortcut() {
        JsonAdaptedShortcut shortcut = new JsonAdaptedShortcut(SHORTCUT);
        JsonAdaptedShortcut expectedShortcut = new JsonAdaptedShortcut(VALID_SHORTCUT);
        assertEquals(shortcut.getShortcut(), expectedShortcut.getShortcut());
    }

    @Test
    public void toModelType_validShortcut_returnsShortcut() throws Exception {
        JsonAdaptedShortcut shortcut = new JsonAdaptedShortcut(VALID_SHORTCUT);
        assertEquals(SHORTCUT, shortcut.toModelType());
    }

    @Test
    public void toModelType_invalidShortcutName_throwsIllegalValueException() {
        JsonAdaptedShortcut shortcut =
                new JsonAdaptedShortcut(INVALID_NAME_SHORTCUT);
        String expectedMessage = Shortcut.MESSAGE_NAME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, shortcut::toModelType);
    }

    @Test
    public void toModelType_invalidShortcutCommand_throwsIllegalValueException() {
        JsonAdaptedShortcut shortcut =
                new JsonAdaptedShortcut(INVALID_COMMAND_SHORTCUT);
        String expectedMessage = Shortcut.MESSAGE_COMMAND_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, shortcut::toModelType);
    }

    @Test
    public void getShortcut_returnsShortcutInString() {
        JsonAdaptedShortcut shortcut =
                new JsonAdaptedShortcut(VALID_SHORTCUT);
        assertTrue(shortcut.getShortcut().equals(VALID_SHORTCUT));
    }

}
