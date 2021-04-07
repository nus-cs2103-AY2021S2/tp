package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_DOG_PREDICATE;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_OWNER_PREDICATE;
import static dog.pawbook.model.managedentity.IsEntityPredicate.IS_PROGRAM_PREDICATE;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
        expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
    }

    @Test
    public void execute_listDogs_success() {
        expectedModel.updateFilteredEntityList(IS_DOG_PREDICATE);
        assertCommandSuccess(new ListCommand(IS_DOG_PREDICATE, Dog.ENTITY_WORD), model,
                String.format(ListCommand.MESSAGE_SUCCESS_FORMAT, Dog.ENTITY_WORD), expectedModel);
    }

    @Test
    public void execute_listOwners_success() {
        expectedModel.updateFilteredEntityList(IS_OWNER_PREDICATE);
        assertCommandSuccess(new ListCommand(IS_OWNER_PREDICATE, Owner.ENTITY_WORD), model,
                String.format(ListCommand.MESSAGE_SUCCESS_FORMAT, Owner.ENTITY_WORD), expectedModel);
    }

    @Test
    public void execute_listPrograms_showEmptyList() {
        expectedModel.updateFilteredEntityList(IS_PROGRAM_PREDICATE);
        assertCommandSuccess(new ListCommand(IS_PROGRAM_PREDICATE, Program.ENTITY_WORD), model,
                String.format(ListCommand.MESSAGE_NO_ENTITY_AVAILABLE, Program.ENTITY_WORD), expectedModel);
    }
}
