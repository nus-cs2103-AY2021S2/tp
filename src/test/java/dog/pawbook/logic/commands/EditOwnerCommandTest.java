package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalEntities.getTypicalDatabase;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_THREE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.EditOwnerDescriptorBuilder;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditOwnerCommandTest {

    private Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Pair<Integer, Entity> firstIdEntity = model.getDatabase().getEntityList().get(0);
        Owner editedOwner = new OwnerBuilder((Owner) firstIdEntity.getValue()).build();
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(editedOwner).build();
        EditOwnerCommand editOwnerCommand = new EditOwnerCommand(firstIdEntity.getKey(), descriptor);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        expectedModel.setEntity(firstIdEntity.getKey(), editedOwner);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(firstIdEntity.getKey()));
        assertCommandSuccess(editOwnerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Owner toEditOwner = (Owner) model.getEntity(ID_THREE);

        OwnerBuilder ownerInList = new OwnerBuilder(toEditOwner);
        Owner editedOwner = ownerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(ID_THREE, descriptor);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_THREE, editedOwner);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_THREE));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(ID_ONE, new EditOwnerDescriptor());
        Owner editedOwner = (Owner) model.getEntity(ID_ONE);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_ONE));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    // todo: restore execute_invalidOwnerIdUnfilteredList_failure after identity crisis is solved

    @Test
    public void execute_invalidOwnerId_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(outOfBoundId, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_INVALID_OWNER_ID);
    }

    @Test
    public void equals() {
        final EditOwnerCommand standardCommand = new EditOwnerCommand(ID_ONE, DESC_AMY);

        // same values -> returns true
        EditOwnerDescriptor copyDescriptor = new EditOwnerDescriptor(DESC_AMY);
        EditOwnerCommand commandWithSameValues = new EditOwnerCommand(ID_ONE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOwnerCommand(ID_TWO, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOwnerCommand(ID_ONE, DESC_BOB)));
    }

}
