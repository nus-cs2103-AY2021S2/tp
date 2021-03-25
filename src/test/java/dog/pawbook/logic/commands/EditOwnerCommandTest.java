package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.testutil.TypicalIndexes.ID_FIRST_OWNER;
import static dog.pawbook.testutil.TypicalIndexes.ID_SECOND_OWNER;
import static dog.pawbook.testutil.TypicalIndexes.ID_THIRD_OWNER;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.testutil.EditOwnerDescriptorBuilder;
import dog.pawbook.testutil.OwnerBuilder;
import javafx.util.Pair;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditOwnerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Owner editedOwner = new OwnerBuilder().build();
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(editedOwner).build();
        EditOwnerCommand editOwnerCommand = new EditOwnerCommand(ID_FIRST_OWNER, descriptor);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEntity(ID_FIRST_OWNER, editedOwner);

        assertCommandSuccess(editOwnerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Owner toEditOwner = (Owner) model.getEntity(ID_THIRD_OWNER);

        OwnerBuilder ownerInList = new OwnerBuilder(toEditOwner);
        Owner editedOwner = ownerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(ID_THIRD_OWNER, descriptor);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEntity(ID_THIRD_OWNER, editedOwner);

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(ID_FIRST_OWNER, new EditOwnerDescriptor());
        Owner editedOwner = (Owner) model.getEntity(ID_FIRST_OWNER);

        String expectedMessage = String.format(EditOwnerCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editEntityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateOwnerUnfilteredList_failure() {
        Owner firstOwner = (Owner) model.getEntity(ID_FIRST_OWNER);
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(firstOwner).build();
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(ID_SECOND_OWNER, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_DUPLICATE_OWNER);
    }

    @Test
    public void execute_invalidOwnerIdUnfilteredList_failure() {
        Integer outOfBoundId = model.getUnfilteredEntityList().stream()
                .map(Pair::getKey).sorted().collect(toList()).get(model.getUnfilteredEntityList().size() - 1) + 1;
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditOwnerCommand editEntityCommand = new EditOwnerCommand(outOfBoundId, descriptor);

        assertCommandFailure(editEntityCommand, model, Messages.MESSAGE_INVALID_OWNER_ID);
    }

    @Test
    public void equals() {
        final EditOwnerCommand standardCommand = new EditOwnerCommand(ID_FIRST_OWNER, DESC_AMY);

        // same values -> returns true
        EditOwnerDescriptor copyDescriptor = new EditOwnerDescriptor(DESC_AMY);
        EditOwnerCommand commandWithSameValues = new EditOwnerCommand(ID_FIRST_OWNER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOwnerCommand(ID_SECOND_OWNER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOwnerCommand(ID_FIRST_OWNER, DESC_BOB)));
    }

}
