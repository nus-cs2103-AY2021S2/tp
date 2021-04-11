package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPassengerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBookPassengers;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.testutil.EditPassengerDescriptorBuilder;
import seedu.address.testutil.PassengerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookPassengers(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Passenger editedPassenger = new PassengerBuilder().build();
        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder(editedPassenger).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PASSENGER_SUCCESS, editedPassenger);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPassenger(model.getFilteredPassengerList().get(0), editedPassenger);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPassenger = Index.fromOneBased(model.getFilteredPassengerList().size());
        Passenger lastPassenger = model.getFilteredPassengerList().get(indexLastPassenger.getZeroBased());

        PassengerBuilder passengerInList = new PassengerBuilder(lastPassenger);
        Passenger editedPassenger = passengerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HR).build();

        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HR).build();
        EditCommand editCommand = new EditCommand(indexLastPassenger, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PASSENGER_SUCCESS, editedPassenger);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPassenger(lastPassenger, editedPassenger);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST, new EditCommand.EditPassengerDescriptor());
        Passenger editedPassenger = model.getFilteredPassengerList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PASSENGER_SUCCESS, editedPassenger);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPassengerAtIndex(model, INDEX_FIRST);

        Passenger passengerInFilteredList = model.getFilteredPassengerList().get(INDEX_FIRST.getZeroBased());
        Passenger editedPassenger = new PassengerBuilder(passengerInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditPassengerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PASSENGER_SUCCESS, editedPassenger);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPassenger(model.getFilteredPassengerList().get(0), editedPassenger);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePassengerUnfilteredList_failure() {
        Passenger firstPassenger = model.getFilteredPassengerList().get(INDEX_FIRST.getZeroBased());
        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder(firstPassenger).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PASSENGER);
    }

    @Test
    public void execute_duplicatePassengerFilteredList_failure() {
        showPassengerAtIndex(model, INDEX_FIRST);

        // edit passenger in filtered list into a duplicate in address book
        Passenger passengerInList = model.getAddressBook()
                .getPassengerList().get(INDEX_SECOND.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditPassengerDescriptorBuilder(passengerInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PASSENGER);
    }

    @Test
    public void execute_invalidPassengerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPassengerList().size() + 1);
        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPassengerIndexFilteredList_failure() {
        showPassengerAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPassengerList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPassengerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditCommand.EditPassengerDescriptor copyDescriptor = new EditCommand.EditPassengerDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST, DESC_BOB)));
    }

}
