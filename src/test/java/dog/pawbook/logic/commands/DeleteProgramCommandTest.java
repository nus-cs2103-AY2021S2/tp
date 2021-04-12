//@@author branzuelajohn
package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ID_MISMATCH_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ID_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_PROGRAM_PREDICATE;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_FIFTEEN;
import static dog.pawbook.testutil.TypicalId.ID_SIXTEEN;
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
 * {@code DeleteProgramCommand}.
 */
public class DeleteProgramCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Entity entity = model.getEntity(ID_FIFTEEN);
        DeleteProgramCommand deleteProgramCommand = new DeleteProgramCommand(ID_FIFTEEN);

        String expectedMessage = DeleteProgramCommand.MESSAGE_SUCCESS + entity;

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.deleteEntity(ID_FIFTEEN);
        assertCommandSuccess(deleteProgramCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        List<Integer> indices = model.getUnfilteredEntityList().stream().map(Pair::getKey).sorted().collect(toList());
        int outOfBoundId = indices.get(indices.size() - 1) + 1;
        DeleteProgramCommand deleteProgramCommand = new DeleteProgramCommand(outOfBoundId);

        assertCommandFailure(deleteProgramCommand, model, Messages.MESSAGE_INVALID_PROGRAM_ID);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        model.updateFilteredEntityList(IS_PROGRAM_PREDICATE);

        Entity entity = model.getEntity(ID_FIFTEEN);
        DeleteProgramCommand deleteProgramCommand = new DeleteProgramCommand(ID_FIFTEEN);

        String expectedMessage = DeleteProgramCommand.MESSAGE_SUCCESS + entity;

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.updateFilteredEntityList(IS_PROGRAM_PREDICATE);
        expectedModel.deleteEntity(ID_FIFTEEN);
        assertCommandSuccess(deleteProgramCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIdNotProgramFilteredList_throwsCommandException() {
        model.updateFilteredEntityList(IS_PROGRAM_PREDICATE);

        int notProgramId = model.getUnfilteredEntityList().get(0).getKey();
        DeleteProgramCommand deleteProgramCommand = new DeleteProgramCommand(notProgramId);

        assertCommandFailure(deleteProgramCommand, model, String.format(MESSAGE_ID_MISMATCH_FORMAT, "program"));
    }

    @Test
    public void execute_invalidIdFilteredList_throwsCommandException() {
        model.updateFilteredEntityList(IS_PROGRAM_PREDICATE);

        List<Integer> indices = model.getUnfilteredEntityList().stream().map(Pair::getKey).sorted().collect(toList());
        int outOfBoundId = indices.get(indices.size() - 1) + 1;
        DeleteProgramCommand deleteProgramCommand = new DeleteProgramCommand(outOfBoundId);

        assertCommandFailure(deleteProgramCommand, model, String.format(MESSAGE_INVALID_ID_FORMAT, "program"));
    }

    @Test
    public void equals() {
        DeleteProgramCommand deleteFirstCommand = new DeleteProgramCommand(ID_FIFTEEN);
        DeleteProgramCommand deleteSecondCommand = new DeleteProgramCommand(ID_SIXTEEN);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteProgramCommand deleteFirstCommandCopy = new DeleteProgramCommand(ID_FIFTEEN);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different program -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
