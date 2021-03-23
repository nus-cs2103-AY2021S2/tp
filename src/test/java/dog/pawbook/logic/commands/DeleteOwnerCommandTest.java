package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_OWNER;
import static dog.pawbook.testutil.TypicalIndexes.ID_SECOND_OWNER;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteOwnerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Pair<Integer, Entity> pair = model.getFilteredEntityList().get(0);
        int id = pair.getKey();
        Entity entity = pair.getValue();
        DeleteOwnerCommand deleteOwnerCommand = new DeleteOwnerCommand(id);

        String expectedMessage = DeleteOwnerCommand.MESSAGE_SUCCESS + entity;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEntity(pair.getKey());

        assertCommandSuccess(deleteOwnerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        List<Integer> indices = model.getFilteredEntityList().stream().map(Pair::getKey).sorted().collect(toList());
        int outOfBoundId = indices.get(indices.size() - 1) + 1;
        DeleteOwnerCommand deleteOwnerCommand = new DeleteOwnerCommand(outOfBoundId);

        assertCommandFailure(deleteOwnerCommand, model, Messages.MESSAGE_INVALID_OWNER_ID);
    }

    @Test
    public void equals() {
        DeleteOwnerCommand deleteFirstCommand = new DeleteOwnerCommand(ID_FIRST_OWNER);
        DeleteOwnerCommand deleteSecondCommand = new DeleteOwnerCommand(ID_SECOND_OWNER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteOwnerCommand deleteFirstCommandCopy = new DeleteOwnerCommand(ID_FIRST_OWNER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different owner -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
