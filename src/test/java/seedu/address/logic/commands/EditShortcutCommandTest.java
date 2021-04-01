package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalShortcuts.getTypicalShortcutLibrary;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.shortcut.Shortcut;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditShortcutCommand.
 */
public class EditShortcutCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalShortcutLibrary());

    @Test
    public void execute_validShortcutNameAndCommand_success() {
        Shortcut editedShortcut = new Shortcut("ls", "list");
        EditShortcutCommand editShortcutCommand = new EditShortcutCommand(editedShortcut.getShortcutName(),
                editedShortcut.getShortcutCommand());

        String expectedMessage = String.format(EditShortcutCommand.MESSAGE_EDIT_SHORTCUT_SUCCESS, editedShortcut);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalShortcutLibrary());
        expectedModel.setShortcut(editedShortcut.getShortcutName(), editedShortcut.getShortcutCommand());

        assertCommandSuccess(editShortcutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidShortcutName_failure() {
        Shortcut editedShortcut = new Shortcut("abc", "list");
        EditShortcutCommand editShortcutCommand = new EditShortcutCommand(editedShortcut.getShortcutName(),
                editedShortcut.getShortcutCommand());

        assertCommandFailure(editShortcutCommand, model, Messages.MESSAGE_SHORTCUT_NAME_NOT_EXIST);
    }

    @Test
    public void equals() {
        final EditShortcutCommand first = new EditShortcutCommand("sna", "sort -n -asc");
        final EditShortcutCommand second = new EditShortcutCommand("sna", "sort -n -asc");

        // same values -> returns true
        assertTrue(first.equals(second));

        // same object -> returns true
        assertTrue(first.equals(first));

        // null -> returns false
        assertFalse(first.equals(null));

        // different types -> returns false
        assertFalse(first.equals(1));

        // different shortcut name -> returns false
        assertFalse(first.equals(new EditShortcutCommand("sortname", "sort -n -asc")));

        // different shortcut command -> returns false
        assertFalse(first.equals(new EditShortcutCommand("sna", "list")));
    }
}
