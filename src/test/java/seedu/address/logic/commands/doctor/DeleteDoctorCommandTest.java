package seedu.address.logic.commands.doctor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteDoctorCommandTest {
    // empty appointment schedule to prevent conflict during delete operations
    private final Model model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
            new AppointmentSchedule(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false);

        String expectedMessage = String.format(Messages.MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(model.getPatientRecords()),
                new AddressBook<>(model.getDoctorRecords()),
                new AppointmentSchedule(model.getAppointmentSchedule()),
                new UserPrefs(model.getUserPrefs())
        );

        expectedModel.deleteDoctor(doctorToDelete);
        assertCommandSuccess(deleteDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex, false);

        assertCommandFailure(deleteDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_IN_LIST);

        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false);

        String expectedMessage = String.format(Messages.MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(model.getPatientRecords()),
                new AddressBook<>(model.getDoctorRecords()),
                new AppointmentSchedule(model.getAppointmentSchedule()),
                new UserPrefs(model.getUserPrefs())
        );

        expectedModel.deleteDoctor(doctorToDelete);
        showNoDoctors(expectedModel);

        assertCommandSuccess(deleteDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDoctorAtIndex(model, INDEX_FIRST_IN_LIST);

        Index outOfBoundIndex = INDEX_SECOND_IN_LIST;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDoctorRecords().getPersonList().size());

        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex, false);

        assertCommandFailure(deleteDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteDoctorCommand deleteFirstCommand = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false);
        DeleteDoctorCommand deleteSecondCommand = new DeleteDoctorCommand(INDEX_SECOND_IN_LIST, false);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDoctorCommand deleteFirstCommandCopy = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false);
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
    private void showNoDoctors(Model model) {
        model.updateFilteredDoctorList(p -> false);

        assertTrue(model.getFilteredDoctorList().isEmpty());
    }
}
