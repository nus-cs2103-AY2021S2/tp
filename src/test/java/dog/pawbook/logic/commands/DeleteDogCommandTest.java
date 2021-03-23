package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.logic.commands.CommandTestUtil.showDogAtIndex;
import static dog.pawbook.testutil.TypicalDogs.getTypicalAddressBook;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_FIRST_DOG;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_SECOND_DOG;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.core.index.Index;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteDogCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Pair<Integer, Entity> pair = model.getFilteredEntityList().get(0);
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(Index.fromZeroBased(pair.getKey()));

        String expectedMessage = DeleteDogCommand.MESSAGE_SUCCESS + pair.getValue();

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEntity(pair.getKey());

        assertCommandSuccess(deleteDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        List<Integer> indices = model.getFilteredEntityList().stream().map(Pair::getKey).sorted().collect(toList());
        Index outOfBoundIndex = Index.fromZeroBased(indices.get(indices.size() - 1) + 1);
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(outOfBoundIndex);

        assertCommandFailure(deleteDogCommand, model, Messages.MESSAGE_INVALID_DOG_DISPLAYED_ID);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDogAtIndex(model, INDEX_FIRST_DOG);

        Entity entityToDelete = model.getFilteredEntityList().get(0).getValue();
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(INDEX_FIRST_DOG);

        String expectedMessage = DeleteDogCommand.MESSAGE_SUCCESS + entityToDelete;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEntity(model.getFilteredEntityList().get(0).getKey());
        showNoDog(expectedModel);

        assertCommandSuccess(deleteDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDogAtIndex(model, INDEX_FIRST_DOG);

        Index outOfBoundIndex = INDEX_SECOND_DOG;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEntityList().size());

        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(outOfBoundIndex);

        assertCommandFailure(deleteDogCommand, model, Messages.MESSAGE_INVALID_DOG_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        DeleteDogCommand deleteFirstCommand = new DeleteDogCommand(INDEX_FIRST_DOG);
        DeleteDogCommand deleteSecondCommand = new DeleteDogCommand(INDEX_SECOND_DOG);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDogCommand deleteFirstCommandCopy = new DeleteDogCommand(INDEX_FIRST_DOG);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different dog -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDog(Model model) {
        model.updateFilteredEntityList(p -> false);

        assertTrue(model.getFilteredEntityList().isEmpty());
    }
}
