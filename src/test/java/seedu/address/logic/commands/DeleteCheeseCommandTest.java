package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCheeseAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CHEESE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CHEESE;
import static seedu.address.testutil.TypicalModels.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.util.ModelPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCheeseCommand}.
 */
public class DeleteCheeseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Cheese cheeseToDelete = model.getFilteredCheeseList().get(INDEX_FIRST_CHEESE.getZeroBased());
        DeleteCheeseCommand deleteCheeseCommand = new DeleteCheeseCommand(INDEX_FIRST_CHEESE);

        String expectedMessage = String.format(DeleteCheeseCommand.MESSAGE_DELETE_CHEESE_SUCCESS,
                cheeseToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCheese(cheeseToDelete);
        expectedModel.setPanelToCheeseList();

        assertCommandSuccess(deleteCheeseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCheeseList().size() + 1);
        DeleteCheeseCommand deleteCheeseCommand = new DeleteCheeseCommand(outOfBoundIndex);

        assertCommandFailure(deleteCheeseCommand, model, Messages.MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCheeseAtIndex(model, INDEX_FIRST_CHEESE);

        Cheese cheeseToDelete = model.getFilteredCheeseList().get(INDEX_FIRST_CHEESE.getZeroBased());
        DeleteCheeseCommand deleteCheeseCommand = new DeleteCheeseCommand(INDEX_FIRST_CHEESE);

        String expectedMessage = String.format(DeleteCheeseCommand.MESSAGE_DELETE_CHEESE_SUCCESS,
                cheeseToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteCheese(cheeseToDelete);
        showNoCheese(expectedModel);
        expectedModel.setPanelToCheeseList();

        assertCommandSuccess(deleteCheeseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCheeseAtIndex(model, INDEX_FIRST_CHEESE);

        Index outOfBoundIndex = INDEX_SECOND_CHEESE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCheeseList().size());

        DeleteCheeseCommand deleteCheeseCommand = new DeleteCheeseCommand(outOfBoundIndex);

        assertCommandFailure(deleteCheeseCommand, model, Messages.MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCheeseCommand deleteFirstCommand = new DeleteCheeseCommand(INDEX_FIRST_CHEESE);
        DeleteCheeseCommand deleteSecondCommand = new DeleteCheeseCommand(INDEX_SECOND_CHEESE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCheeseCommand deleteFirstCommandCopy = new DeleteCheeseCommand(INDEX_FIRST_CHEESE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different cheese -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCheese(Model model) {
        model.updateFilteredCheeseList(ModelPredicate.getEmptyPredicate());

        assertTrue(model.getFilteredCheeseList().isEmpty());
    }
}
