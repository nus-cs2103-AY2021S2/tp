package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.OwnerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newOwner_success() {
        Owner validOwner = new OwnerBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEntity(validOwner);

        assertCommandSuccess(new AddOwnerCommand(validOwner), model,
                AddOwnerCommand.MESSAGE_SUCCESS + validOwner, expectedModel);
    }

    @Test
    public void execute_duplicateOwner_throwsCommandException() {
        Entity entityInList = model.getAddressBook().getEntityList().get(0).getValue();
        assertCommandFailure(new AddOwnerCommand((Owner) entityInList), model, Messages.MESSAGE_DUPLICATE_OWNER);
    }

}
