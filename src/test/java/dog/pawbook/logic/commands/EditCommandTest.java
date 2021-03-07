package dog.pawbook.logic.commands;

import static dog.pawbook.logic.commands.CommandTestUtil.DESC_AMY;
import static dog.pawbook.logic.commands.CommandTestUtil.DESC_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static dog.pawbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static dog.pawbook.logic.commands.CommandTestUtil.showOwnerAtIndex;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_FIRST_OWNER;
import static dog.pawbook.testutil.TypicalIndexes.INDEX_SECOND_OWNER;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.core.index.Index;
import dog.pawbook.logic.commands.EditCommand.EditOwnerDescriptor;
import dog.pawbook.model.AddressBook;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.owner.Owner;
import dog.pawbook.testutil.EditOwnerDescriptorBuilder;
import dog.pawbook.testutil.OwnerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Owner editedOwner = new OwnerBuilder().build();
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(editedOwner).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OWNER, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOwner(model.getFilteredOwnerList().get(0), editedOwner);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastOwner = Index.fromOneBased(model.getFilteredOwnerList().size());
        Owner lastOwner = model.getFilteredOwnerList().get(indexLastOwner.getZeroBased());

        OwnerBuilder ownerInList = new OwnerBuilder(lastOwner);
        Owner editedOwner = ownerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastOwner, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOwner(lastOwner, editedOwner);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OWNER, new EditOwnerDescriptor());
        Owner editedOwner = model.getFilteredOwnerList().get(INDEX_FIRST_OWNER.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showOwnerAtIndex(model, INDEX_FIRST_OWNER);

        Owner ownerInFilteredList = model.getFilteredOwnerList().get(INDEX_FIRST_OWNER.getZeroBased());
        Owner editedOwner = new OwnerBuilder(ownerInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OWNER,
                new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_OWNER_SUCCESS, editedOwner);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOwner(model.getFilteredOwnerList().get(0), editedOwner);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateOwnerUnfilteredList_failure() {
        Owner firstOwner = model.getFilteredOwnerList().get(INDEX_FIRST_OWNER.getZeroBased());
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder(firstOwner).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_OWNER, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_OWNER);
    }

    @Test
    public void execute_duplicateOwnerFilteredList_failure() {
        showOwnerAtIndex(model, INDEX_FIRST_OWNER);

        // edit owner in filtered list into a duplicate in address book
        Owner ownerInList = model.getAddressBook().getOwnerList().get(INDEX_SECOND_OWNER.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OWNER,
                new EditOwnerDescriptorBuilder(ownerInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_OWNER);
    }

    @Test
    public void execute_invalidOwnerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOwnerList().size() + 1);
        EditOwnerDescriptor descriptor = new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_OWNER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidOwnerIndexFilteredList_failure() {
        showOwnerAtIndex(model, INDEX_FIRST_OWNER);
        Index outOfBoundIndex = INDEX_SECOND_OWNER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOwnerList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditOwnerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_OWNER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_OWNER, DESC_AMY);

        // same values -> returns true
        EditOwnerDescriptor copyDescriptor = new EditOwnerDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_OWNER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new HelpCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_OWNER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_OWNER, DESC_BOB)));
    }

}
