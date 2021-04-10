//@@author ZhangAnli
package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW;
import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW_FOR_ONE;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ENTITY_ID;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_EIGHTEEN;
import static dog.pawbook.testutil.TypicalId.ID_FIFTEEN;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.ViewCommandComparator;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCommand.
 */
public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());

    @BeforeEach
    public void resetState() {
        model.updateFilteredEntityList(x -> x.getValue() instanceof Dog);
    }

    @Test
    public void execute_invalidId_failure() {
        // out of bounds integer failure
        assertCommandFailure(new ViewCommand(model.getUnfilteredEntityList().size() + 1),
                model, MESSAGE_INVALID_ENTITY_ID);
    }

    @Test
    public void execute_validOwnerId_success() {
        // view dog success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 1 || x.getKey() == 2);
        expectedModel.sortEntities(new ViewCommandComparator(Owner.class));

        assertCommandSuccess(new ViewCommand(ID_ONE), model, String.format(
                MESSAGE_ENTITIES_LISTED_OVERVIEW, expectedModel.getFilteredEntityList().size()), expectedModel);
        assertTrue(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));

        // assert order Owner -> Dog
        assertTrue(expectedModel.getFilteredEntityList().get(0).getValue() instanceof Owner);
        assertTrue(expectedModel.getFilteredEntityList().get(1).getValue() instanceof Dog);
    }

    @Test
    public void execute_validDogId_success() {
        // view owner success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 1 || x.getKey() == 2 || x.getKey() == 18);
        expectedModel.sortEntities(new ViewCommandComparator(Dog.class));

        assertCommandSuccess(new ViewCommand(ID_TWO), model, String.format(
                MESSAGE_ENTITIES_LISTED_OVERVIEW, expectedModel.getFilteredEntityList().size()), expectedModel);
        assertTrue(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));

        // assert order Dog -> Owner -> Program
        assertTrue(expectedModel.getFilteredEntityList().get(0).getValue() instanceof Dog);
        assertTrue(expectedModel.getFilteredEntityList().get(1).getValue() instanceof Owner);
        assertTrue(expectedModel.getFilteredEntityList().get(2).getValue() instanceof Program);
    }

    @Test
    public void execute_validProgramId_success() {
        // view program success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 2 || x.getKey() == 18);
        expectedModel.sortEntities(new ViewCommandComparator(Program.class));

        assertCommandSuccess(new ViewCommand(ID_EIGHTEEN), model, String.format(
                MESSAGE_ENTITIES_LISTED_OVERVIEW, expectedModel.getFilteredEntityList().size()), expectedModel);
        assertTrue(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));

        // assert order Program -> Dog
        assertTrue(model.getFilteredEntityList().get(0).getValue() instanceof Program);
        assertTrue(model.getFilteredEntityList().get(1).getValue() instanceof Dog);
    }

    @Test
    public void execute_validSingleId_success() {
        // view single valid ID success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 15);
        expectedModel.sortEntities(new ViewCommandComparator(Program.class));

        assertCommandSuccess(new ViewCommand(ID_FIFTEEN), model,
                MESSAGE_ENTITIES_LISTED_OVERVIEW_FOR_ONE, expectedModel);
        assertTrue(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));
        assertTrue(model.getFilteredEntityList().get(0).getValue() instanceof Program);
    }

    @Test
    public void equals() {

        ViewCommand firstViewCommand = new ViewCommand(1);
        ViewCommand secondViewCommand = new ViewCommand(1);
        ViewCommand thirdViewCommand = new ViewCommand(2);

        // short circuit success
        assertEquals(firstViewCommand, firstViewCommand);

        // same command success
        assertEquals(firstViewCommand, secondViewCommand);

        // different command failure
        assertNotEquals(firstViewCommand, thirdViewCommand);

        // different types -> returns false
        assertFalse(firstViewCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewCommand.equals(null));

    }
}
