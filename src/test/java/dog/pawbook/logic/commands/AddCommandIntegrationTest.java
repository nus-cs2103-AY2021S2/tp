package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.owner.Owner;
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
        expectedModel.addOwner(validOwner);

        assertCommandSuccess(new AddCommand(validOwner), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validOwner), expectedModel);
    }

    @Test
    public void execute_duplicateOwner_throwsCommandException() {
        Owner ownerInList = model.getAddressBook().getOwnerList().get(0);
        assertCommandFailure(new AddCommand(ownerInList), model, AddCommand.MESSAGE_DUPLICATE_OWNER);
    }

}
