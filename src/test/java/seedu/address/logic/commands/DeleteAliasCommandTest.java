package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS;
import static seedu.address.testutil.TypicalAliases.DELETE_ALIAS;
import static seedu.address.testutil.TypicalAliases.INVALID_ALIAS;
import static seedu.address.testutil.TypicalAliases.getTypicalAlias;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalAliases.getTypicalCommandAlias;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAliasCommand}.
 */
public class DeleteAliasCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliases());

    @Test
    public void execute_validAlias_success() {
        CommandAlias commandAliasToDelete = getTypicalCommandAlias();
        Alias aliasToDelete = getTypicalAlias();
        DeleteAliasCommand deleteAliasCommand = new DeleteAliasCommand(aliasToDelete);

        String expectedMessage = String.format(DeleteAliasCommand.MESSAGE_SUCCESS, commandAliasToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getAliases());
        expectedModel.deleteAlias(aliasToDelete);

        assertCommandSuccess(deleteAliasCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_aliasNotFound_throwsCommandException() {
        DeleteAliasCommand deleteAliasCommand = new DeleteAliasCommand(INVALID_ALIAS);

        assertCommandFailure(deleteAliasCommand, model, DeleteAliasCommand.MESSAGE_ALIAS_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteAliasCommand deleteFirstCommand = new DeleteAliasCommand(ADD_ALIAS);
        DeleteAliasCommand deleteSecondCommand = new DeleteAliasCommand(DELETE_ALIAS);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAliasCommand deleteFirstCommandCopy = new DeleteAliasCommand(ADD_ALIAS);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
