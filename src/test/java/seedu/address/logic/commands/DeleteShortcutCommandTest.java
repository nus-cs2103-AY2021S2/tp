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
import seedu.address.storage.Authentication;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteShortcutCommand}.
 */
public class DeleteShortcutCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), new Authentication(),
            getTypicalShortcutLibrary());

    @Test
    public void execute_validShortcutName_success() {
        String nameOfShortcutToDelete = "ls";
        DeleteShortcutCommand deleteShortcutCommand = new DeleteShortcutCommand("ls");

        String expectedMessage = String.format(DeleteShortcutCommand.MESSAGE_DELETE_SHORTCUT_SUCCESS,
                nameOfShortcutToDelete);

        ModelManager expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new Authentication(),
                getTypicalShortcutLibrary());
        expectedModel.deleteShortcut(nameOfShortcutToDelete);

        assertCommandSuccess(deleteShortcutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidShortcutName_throwsCommandException() {
        String nameOfNonExistentShortcut = "abc";
        DeleteShortcutCommand deleteShortcutCommand = new DeleteShortcutCommand(nameOfNonExistentShortcut);

        assertCommandFailure(deleteShortcutCommand, model, Messages.MESSAGE_SHORTCUT_NAME_NOT_EXIST);
    }

    @Test
    public void equals() {
        DeleteShortcutCommand first = new DeleteShortcutCommand("sna");
        DeleteShortcutCommand second = new DeleteShortcutCommand("ls");

        // same object -> returns true
        assertTrue(first.equals(first));

        // same values -> returns true
        DeleteShortcutCommand firstCopy = new DeleteShortcutCommand("sna");
        assertTrue(first.equals(firstCopy));

        // different types -> returns false
        assertFalse(first.equals(1));

        // null -> returns false
        assertFalse(first.equals(null));

        // different shortcut name -> returns false
        assertFalse(first.equals(second));
    }

}
