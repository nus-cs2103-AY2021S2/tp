//@@author ZhangAnli
package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW;
import static dog.pawbook.commons.core.Messages.MESSAGE_ENTITIES_LISTED_OVERVIEW_FOR_ONE;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ENTITY_ID;
import static dog.pawbook.logic.commands.CommandTestUtil.INVALID_OUT_OF_BOUNDS_ID_INTEGER;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_EIGHTEEN;
import static dog.pawbook.testutil.TypicalId.ID_FIFTEEN;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;

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

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_invalidId_failure() {
        // out of bounds integer failure
        assertCommandFailure(new ViewCommand(INVALID_OUT_OF_BOUNDS_ID_INTEGER), model, MESSAGE_INVALID_ENTITY_ID);
    }

    @Test
    public void execute_validOwnerId_success() {
        // view dog success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 1 || x.getKey() == 2);
        expectedModel.sortEntities(new ViewCommandComparator(Owner.class));

        assertCommandSuccess(new ViewCommand(ID_ONE), model, String.format(
                MESSAGE_ENTITIES_LISTED_OVERVIEW, expectedModel.getFilteredEntityList().size()), expectedModel);
        assert(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));

        // assert order Owner -> Dog
        assert(expectedModel.getFilteredEntityList().get(0).getValue() instanceof Owner);
        assert(expectedModel.getFilteredEntityList().get(1).getValue() instanceof Dog);
    }

    @Test
    public void execute_validDogId_success() {
        // view owner success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 1 || x.getKey() == 2 || x.getKey() == 18);
        expectedModel.sortEntities(new ViewCommandComparator(Dog.class));

        assertCommandSuccess(new ViewCommand(ID_TWO), model, String.format(
                MESSAGE_ENTITIES_LISTED_OVERVIEW, expectedModel.getFilteredEntityList().size()), expectedModel);
        assert(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));

        // assert order Dog -> Owner -> Program
        assert(expectedModel.getFilteredEntityList().get(0).getValue() instanceof Dog);
        assert(expectedModel.getFilteredEntityList().get(1).getValue() instanceof Owner);
        assert(expectedModel.getFilteredEntityList().get(2).getValue() instanceof Program);
    }

    @Test
    public void execute_validProgramId_success() {
        // view program success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 2 || x.getKey() == 18);
        expectedModel.sortEntities(new ViewCommandComparator(Program.class));

        assertCommandSuccess(new ViewCommand(ID_EIGHTEEN), model, String.format(
                MESSAGE_ENTITIES_LISTED_OVERVIEW, expectedModel.getFilteredEntityList().size()), expectedModel);
        assert(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));

        // assert order Program -> Dog
        assert(model.getFilteredEntityList().get(0).getValue() instanceof Program);
        assert(model.getFilteredEntityList().get(1).getValue() instanceof Dog);
    }

    @Test
    public void execute_validSingleId_success() {
        // view single valid ID success
        expectedModel.updateFilteredEntityList(x -> x.getKey() == 15);
        expectedModel.sortEntities(new ViewCommandComparator(Program.class));

        assertCommandSuccess(new ViewCommand(ID_FIFTEEN), model,
                MESSAGE_ENTITIES_LISTED_OVERVIEW_FOR_ONE, expectedModel);
        assert(model.getFilteredEntityList().equals(expectedModel.getFilteredEntityList()));
        assert(model.getFilteredEntityList().get(0).getValue() instanceof Program);
    }

    @Test
    public void equals_sameCommand_success() {

        ViewCommand firstViewCommand = new ViewCommand(1);
        ViewCommand secondViewCommand = new ViewCommand(1);

        // short circuit success
        assert(firstViewCommand.equals(firstViewCommand));

        // same command success
        assert(firstViewCommand).equals(secondViewCommand);
    }

    @Test
    public void equals_differentCommand_failure() {

        ViewCommand firstViewCommand = new ViewCommand(1);
        ViewCommand secondViewCommand = new ViewCommand(2);

        assert(!firstViewCommand.equals(secondViewCommand));
    }
}
