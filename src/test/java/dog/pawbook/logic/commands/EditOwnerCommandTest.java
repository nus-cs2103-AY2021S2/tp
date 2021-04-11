package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_DUPLICATE_OWNER;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_OWNER_ID;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.IdMatchPredicate;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.EditOwnerDescriptorBuilder;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditOwnerCommandTest {

    private final Model model = new ModelManager(getTypicalDatabase(), new UserPrefs());
    private Model expectedModel;

    @Test
    public void execute_allFieldsSpecified_success() {
        Owner owner = (Owner) model.getEntity(ID_ONE);
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(owner).build();
        EditOwnerCommand editOwnerCommand = new EditOwnerCommand(ID_ONE, descriptor);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, owner);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());

        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_ONE));
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
        EditOwnerCommand editOwnerCommand = new EditOwnerCommand(ID_THREE, descriptor);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.setEntity(ID_THREE, editedOwner);
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_THREE));

        assertCommandSuccess(editOwnerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(ID_ONE, new EditOwnerDescriptor());
        Owner editedOwner = (Owner) model.getEntity(ID_ONE);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        expectedModel = new ModelManager(new Database(model.getDatabase()), new UserPrefs());
        expectedModel.updateFilteredEntityList(new IdMatchPredicate(ID_ONE));

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateOwner_failure() {
        Owner firstOwner = (Owner) model.getEntity(ID_ONE);
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(firstOwner).build();
        EditOwnerCommand editOwnerCommand = new EditOwnerCommand(ID_THREE, descriptor);

        assertCommandFailure(editOwnerCommand, model, MESSAGE_DUPLICATE_OWNER);
    }

    @Test
    public void execute_outOfBoundOwnerId_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditOwnerCommand editOwnerCommand = new EditOwnerCommand(outOfBoundId, descriptor);

        assertCommandFailure(editOwnerCommand, model, MESSAGE_INVALID_OWNER_ID);
    }

    @Test
    public void execute_validIdDifferentEntityType_failure() {
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditOwnerCommand editOwnerCommand = new EditOwnerCommand(ID_TWO, descriptor);

        assertCommandFailure(editOwnerCommand, model, MESSAGE_INVALID_OWNER_ID);
    }

    @Test
    public void equals() {
        final EditOwnerCommand standardCommand = new EditOwnerCommand(ID_ONE, DESC_AMY);

        // same values -> returns true
        EditOwnerDescriptor copyDescriptor = new EditOwnerDescriptor(DESC_AMY);
        EditOwnerCommand commandWithSameValues = new EditOwnerCommand(ID_ONE, copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new HelpCommand(), standardCommand);

        // different index -> returns false
        assertNotEquals(new EditOwnerCommand(ID_TWO, DESC_AMY), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditOwnerCommand(ID_ONE, DESC_BOB), standardCommand);
    }

}
