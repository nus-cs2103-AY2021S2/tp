package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
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
import dog.pawbook.model.managedentity.owner.Owner;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteOwnerCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Entity entity = model.getEntity(ID_ONE);
        DeleteOwnerCommand deleteOwnerCommand = new DeleteOwnerCommand(ID_ONE);

        String expectedMessage = DeleteOwnerCommand.MESSAGE_SUCCESS + entity;

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.deleteEntity(ID_ONE);
        expectedModel.deleteEntity(((Owner) entity).getDogIdSet().stream().findAny().get());

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
        DeleteOwnerCommand deleteFirstCommand = new DeleteOwnerCommand(ID_ONE);
        DeleteOwnerCommand deleteSecondCommand = new DeleteOwnerCommand(ID_TWO);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteOwnerCommand deleteFirstCommandCopy = new DeleteOwnerCommand(ID_ONE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different owner -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
