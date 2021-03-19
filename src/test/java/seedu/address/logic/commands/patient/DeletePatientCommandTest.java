package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePatientCommandTest {

    private Model model = new ModelManager(getTypicalAppointmentSchedule(), getTypicalAddressBook(),
            new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_IN_LIST);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAppointmentSchedule(), model.getAddressBook(),
                new UserPrefs());

        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex);

        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_IN_LIST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_IN_LIST);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(getTypicalAppointmentSchedule(), model.getAddressBook(),
                new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_IN_LIST);

        Index outOfBoundIndex = INDEX_SECOND_IN_LIST;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex);

        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(INDEX_FIRST_IN_LIST);
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(INDEX_SECOND_IN_LIST);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(INDEX_FIRST_IN_LIST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
