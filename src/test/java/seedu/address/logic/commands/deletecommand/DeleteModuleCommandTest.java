package seedu.address.logic.commands.deletecommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleTitles.TITLE_CS1101;
import static seedu.address.testutil.TypicalModuleTitles.TITLE_CS2101;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModuleCommand}.
 */
public class DeleteModuleCommandTest {

    private final Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());

    @Test
    public void execute_validModuleTitle_success() {

        Module moduleToDelete = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());

        Title title = moduleToDelete.getTitle();

        DeleteModuleCommand deleteCommand = new DeleteModuleCommand(title);
        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getRemindMe(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleTitle_throwsCommandException() {

        Title invalidTitle = new Title("INVALID DELETE TITLE");

        DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(invalidTitle);
        assertCommandFailure(deleteModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_TITLE);

    }

    @Test
    public void equals() {
        DeleteModuleCommand deleteModuleFirstCommand = new DeleteModuleCommand(TITLE_CS1101);
        DeleteModuleCommand deleteModuleSecondCommand = new DeleteModuleCommand(TITLE_CS2101);

        // same object -> returns true
        assertTrue(deleteModuleFirstCommand.equals(deleteModuleFirstCommand));

        // same title -> returns true
        DeleteModuleCommand deleteModuleFirstCommandCopy = new DeleteModuleCommand(TITLE_CS1101);
        assertTrue(deleteModuleFirstCommand.equals(deleteModuleFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteModuleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteModuleFirstCommand.equals(null));

        // different module title -> returns false
        assertFalse(deleteModuleFirstCommand.equals(deleteModuleSecondCommand));
    }


}
