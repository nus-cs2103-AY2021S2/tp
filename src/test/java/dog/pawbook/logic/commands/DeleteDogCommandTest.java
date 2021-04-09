package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ID_MISMATCH_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ID_FORMAT;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_OWNER_PREDICATE;
import static dog.pawbook.testutil.TypicalEntities.ALICE;
import static dog.pawbook.testutil.TypicalEntities.DANCING;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_EIGHTEEN;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteDogCommandTest {

    private Model model;

    @BeforeEach
    public void setupModel() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_validIdUnfilteredList_success() {
        Entity entity = model.getEntity(ID_TWO);
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(ID_TWO);

        String expectedMessage = DeleteDogCommand.MESSAGE_SUCCESS + entity;

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.deleteEntity(ID_TWO);
        expectedModel.setEntity(ID_ONE,
                new Owner(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(), ALICE.getTags()));
        expectedModel.setEntity(ID_EIGHTEEN, new Program(DANCING.getName(), DANCING.getSessions(), DANCING.getTags()));

        assertCommandSuccess(deleteDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIdNotDogUnfilteredList_throwsCommandException() {
        int notDogId = model.getUnfilteredEntityList().get(0).getKey();
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(notDogId);

        assertCommandFailure(deleteDogCommand, model, String.format(MESSAGE_ID_MISMATCH_FORMAT, "dog"));
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        List<Integer> indices = model.getUnfilteredEntityList().stream().map(Pair::getKey).sorted().collect(toList());
        int outOfBoundId = indices.get(indices.size() - 1) + 1;
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(outOfBoundId);

        assertCommandFailure(deleteDogCommand, model, String.format(MESSAGE_INVALID_ID_FORMAT, "dog"));
    }

    @Test
    public void execute_validIdFilteredList_success() {
        model.updateFilteredEntityList(IS_OWNER_PREDICATE);

        Entity entity = model.getEntity(ID_TWO);
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(ID_TWO);

        String expectedMessage = DeleteDogCommand.MESSAGE_SUCCESS + entity;

        ModelManager expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        expectedModel.updateFilteredEntityList(IS_OWNER_PREDICATE);
        expectedModel.deleteEntity(ID_TWO);
        expectedModel.setEntity(ID_ONE,
                new Owner(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(), ALICE.getTags()));
        expectedModel.setEntity(ID_EIGHTEEN, new Program(DANCING.getName(), DANCING.getSessions(), DANCING.getTags()));

        assertCommandSuccess(deleteDogCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIdNotDogFilteredList_throwsCommandException() {
        model.updateFilteredEntityList(IS_OWNER_PREDICATE);

        int notDogId = model.getUnfilteredEntityList().get(0).getKey();
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(notDogId);

        assertCommandFailure(deleteDogCommand, model, String.format(MESSAGE_ID_MISMATCH_FORMAT, "dog"));
    }

    @Test
    public void execute_invalidIdFilteredList_throwsCommandException() {
        model.updateFilteredEntityList(IS_OWNER_PREDICATE);

        List<Integer> indices = model.getUnfilteredEntityList().stream().map(Pair::getKey).sorted().collect(toList());
        int outOfBoundId = indices.get(indices.size() - 1) + 1;
        DeleteDogCommand deleteDogCommand = new DeleteDogCommand(outOfBoundId);

        assertCommandFailure(deleteDogCommand, model, String.format(MESSAGE_INVALID_ID_FORMAT, "dog"));
    }

    @Test
    public void equals() {
        DeleteDogCommand deleteFirstCommand = new DeleteDogCommand(ID_ONE);
        DeleteDogCommand deleteSecondCommand = new DeleteDogCommand(ID_TWO);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteDogCommand deleteFirstCommandCopy = new DeleteDogCommand(ID_ONE);
        assertEquals(deleteFirstCommandCopy, deleteFirstCommand);

        // different types -> returns false
        assertNotEquals(deleteFirstCommand, 1);

        // null -> returns false
        assertNotEquals(deleteFirstCommand, null);

        // different dog -> returns false
        assertNotEquals(deleteSecondCommand, deleteFirstCommand);
    }

}
