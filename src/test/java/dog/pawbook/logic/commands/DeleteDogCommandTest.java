package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalDogs.getTypicalAddressBook;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_DOG;
import static dog.pawbook.testutil.TypicalIndexes.ID_SECOND_DOG;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteDogCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Pair<Integer, Entity> pair = model.getFilteredEntityList().get(1);
        int id = pair.getKey();
        Entity entity = pair.getValue();
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(id);

        String expectedMessage = DeleteDogCommand.MESSAGE_SUCCESS + entity;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEntity(pair.getKey());
        int ownerId = ((Dog) pair.getValue()).getOwnerId();
        Owner owner = (Owner) model.getEntity(ownerId);
        HashSet<Integer> newDogIdSet = new HashSet<>(owner.getDogIdSet());
        newDogIdSet.remove(id);
        expectedModel.setEntity(ownerId, new Owner(owner.getName(), owner.getPhone(), owner.getEmail(),
                owner.getAddress(), owner.getTags(), newDogIdSet));

        assertCommandSuccess(deleteDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        List<Integer> indices = model.getFilteredEntityList().stream().map(Pair::getKey).sorted().collect(toList());
        int outOfBoundIndex = indices.get(indices.size() - 1) + 1;
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(outOfBoundIndex);

        assertCommandFailure(deleteDogCommand, model, Messages.MESSAGE_INVALID_DOG_ID);
    }

    @Test
    public void equals() {
        DeleteDogCommand deleteFirstCommand = new DeleteDogCommand(ID_FIRST_DOG);
        DeleteDogCommand deleteSecondCommand = new DeleteDogCommand(ID_SECOND_DOG);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDogCommand deleteFirstCommandCopy = new DeleteDogCommand(ID_FIRST_DOG);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different dog -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
