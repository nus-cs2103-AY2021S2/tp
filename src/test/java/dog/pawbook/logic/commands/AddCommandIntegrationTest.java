package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.OwnerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    }

    @Test
    public void execute_newOwner_success() {
        Owner validOwner = new OwnerBuilder().build();

        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
        int id = expectedModel.addEntity(validOwner);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(id));

        assertCommandSuccess(new AddOwnerCommand(validOwner), model,
                AddOwnerCommand.MESSAGE_SUCCESS + validOwner, expectedModel);
    }

    @Test
    public void execute_duplicateOwner_throwsCommandException() {
        Entity entityInList = model.getDatabase().getEntityList().get(0).getValue();
        assertCommandFailure(new AddOwnerCommand((Owner) entityInList), model, Messages.MESSAGE_DUPLICATE_OWNER);
    }

}
