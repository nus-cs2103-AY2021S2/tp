package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showResidentAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand.EditResidentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.resident.Resident;
import seedu.address.testutil.EditResidentDescriptorBuilder;
import seedu.address.testutil.ResidentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditResidentCommand.
 */
public class EditResidentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Resident editedResident = new ResidentBuilder().build();
        EditResidentDescriptor descriptor = new EditResidentDescriptorBuilder(editedResident).build();
        EditResidentCommand editResidentCommand = new EditResidentCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditResidentCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setResident(model.getFilteredResidentList().get(0), editedResident);

        assertCommandSuccess(editResidentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastResident = Index.fromOneBased(model.getFilteredResidentList().size());
        Resident lastResident = model.getFilteredResidentList().get(indexLastResident.getZeroBased());

        ResidentBuilder residentInList = new ResidentBuilder(lastResident);
        Resident editedResident = residentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .build();

        EditResidentCommand.EditResidentDescriptor descriptor = new EditResidentDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditResidentCommand editResidentCommand = new EditResidentCommand(indexLastResident, descriptor);

        String expectedMessage = String.format(EditResidentCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setResident(lastResident, editedResident);

        assertCommandSuccess(editResidentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditResidentCommand editResidentCommand = new EditResidentCommand(INDEX_FIRST,
            new EditResidentCommand.EditResidentDescriptor());
        Resident editedResident = model.getFilteredResidentList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditResidentCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editResidentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showResidentAtIndex(model, INDEX_FIRST);

        Resident residentInFilteredList = model.getFilteredResidentList().get(INDEX_FIRST.getZeroBased());
        Resident editedResident = new ResidentBuilder(residentInFilteredList).withName(VALID_NAME_BOB).build();
        EditResidentCommand editResidentCommand = new EditResidentCommand(INDEX_FIRST,
                new EditResidentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditResidentCommand.MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setResident(model.getFilteredResidentList().get(0), editedResident);

        assertCommandSuccess(editResidentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateResidentUnfilteredList_failure() {
        Resident firstResident = model.getFilteredResidentList().get(INDEX_FIRST.getZeroBased());
        EditResidentDescriptor descriptor = new EditResidentDescriptorBuilder(firstResident).build();
        EditResidentCommand editResidentCommand = new EditResidentCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editResidentCommand, model, EditResidentCommand.MESSAGE_DUPLICATE_RESIDENT);
    }

    @Test
    public void execute_duplicateResidentFilteredList_failure() {
        showResidentAtIndex(model, INDEX_FIRST);

        // edit resident in filtered list into a duplicate in address book
        Resident residentInList = model.getAddressBook().getResidentList().get(INDEX_SECOND.getZeroBased());
        EditResidentCommand editResidentCommand = new EditResidentCommand(INDEX_FIRST,
                new EditResidentDescriptorBuilder(residentInList).build());

        assertCommandFailure(editResidentCommand, model, EditResidentCommand.MESSAGE_DUPLICATE_RESIDENT);
    }

    @Test
    public void execute_invalidResidentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredResidentList().size() + 1);
        EditResidentCommand.EditResidentDescriptor descriptor = new EditResidentDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditResidentCommand editResidentCommand = new EditResidentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editResidentCommand, model, Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidResidentIndexFilteredList_failure() {
        showResidentAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getResidentList().size());

        EditResidentCommand editResidentCommand = new EditResidentCommand(outOfBoundIndex,
                new EditResidentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editResidentCommand, model, Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditResidentCommand standardCommand = new EditResidentCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditResidentCommand.EditResidentDescriptor copyDescriptor = new EditResidentDescriptor(DESC_AMY);
        EditResidentCommand commandWithSameValues = new EditResidentCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditResidentCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditResidentCommand(INDEX_FIRST, DESC_BOB)));
    }

}
