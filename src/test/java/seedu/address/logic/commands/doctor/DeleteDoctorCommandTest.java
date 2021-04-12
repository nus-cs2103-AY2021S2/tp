package seedu.address.logic.commands.doctor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_DOCTOR_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_FORCE_DELETE_DOCTOR_REQUIRED;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.logic.commands.doctor.DeleteDoctorCommand.FORCE_DELETE_MESSAGE_USAGE;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_IN_LIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_IN_LIST;

import org.junit.jupiter.api.Test;

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
    private final Model emptyApptScheduleModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
            new AppointmentSchedule(), new UserPrefs());

    @Test
    public void execute_nonForceValidIndexUnfilteredDoctorRecordsNoExistingAppointment_success() {
        Doctor doctorToDelete = emptyApptScheduleModel.getFilteredDoctorList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false);

        String expectedMessage = String.format(MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(emptyApptScheduleModel.getPatientRecords()),
                new AddressBook<>(emptyApptScheduleModel.getDoctorRecords()),
                new AppointmentSchedule(emptyApptScheduleModel.getAppointmentSchedule()),
                new UserPrefs(emptyApptScheduleModel.getUserPrefs())
        );

        expectedModel.deleteDoctor(doctorToDelete);
        assertCommandSuccess(deleteDoctorCommand, emptyApptScheduleModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonForceValidIndexUnfilteredDoctorRecordsExistingAppointment_throwsCommandException() {
        Model nonEmptyApptScheduleModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false);

        String expectedMessage = String.format(MESSAGE_FORCE_DELETE_DOCTOR_REQUIRED, FORCE_DELETE_MESSAGE_USAGE);

        assertCommandFailure(deleteDoctorCommand, nonEmptyApptScheduleModel, expectedMessage);
    }

    @Test
    public void execute_nonForceInvalidIndexUnfilteredDoctorRecordsNoExistingAppointment_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(emptyApptScheduleModel.getFilteredDoctorList().size() + 1);
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex, false);

        assertCommandFailure(deleteDoctorCommand, emptyApptScheduleModel, MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_nonForceValidIndexFilteredDoctorRecordsNoExistingAppointment_success() {
        showDoctorAtIndex(emptyApptScheduleModel, INDEX_FIRST_IN_LIST);

        Doctor doctorToDelete = emptyApptScheduleModel.getFilteredDoctorList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, false);

        String expectedMessage = String.format(MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(emptyApptScheduleModel.getPatientRecords()),
                new AddressBook<>(emptyApptScheduleModel.getDoctorRecords()),
                new AppointmentSchedule(emptyApptScheduleModel.getAppointmentSchedule()),
                new UserPrefs(emptyApptScheduleModel.getUserPrefs())
        );

        expectedModel.deleteDoctor(doctorToDelete);
        showNoDoctors(expectedModel);

        assertCommandSuccess(deleteDoctorCommand, emptyApptScheduleModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonForceInvalidIndexFilteredDoctorRecordsNoExistingAppointment_throwsCommandException() {
        showDoctorAtIndex(emptyApptScheduleModel, INDEX_FIRST_IN_LIST);

        Index outOfBoundIndex = INDEX_SECOND_IN_LIST;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < emptyApptScheduleModel.getDoctorRecords().getPersonList().size());

        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex, false);

        assertCommandFailure(deleteDoctorCommand, emptyApptScheduleModel, MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_forceValidIndexUnfilteredDoctorRecordsExistingAppointment_success() {
        Model nonEmptyApptScheduleModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        Doctor doctorToDelete = nonEmptyApptScheduleModel.getFilteredDoctorList().get(
                INDEX_FIRST_IN_LIST.getZeroBased());

        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_IN_LIST, true);

        String expectedMessage = String.format(MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(nonEmptyApptScheduleModel.getPatientRecords()),
                new AddressBook<>(nonEmptyApptScheduleModel.getDoctorRecords()),
                new AppointmentSchedule(nonEmptyApptScheduleModel.getAppointmentSchedule()),
                new UserPrefs(nonEmptyApptScheduleModel.getUserPrefs())
        );

        expectedModel.deleteDoctorAppointments(doctorToDelete.getUuid());
        expectedModel.deleteDoctor(doctorToDelete);

        assertCommandSuccess(deleteDoctorCommand, nonEmptyApptScheduleModel, expectedMessage, expectedModel);
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
