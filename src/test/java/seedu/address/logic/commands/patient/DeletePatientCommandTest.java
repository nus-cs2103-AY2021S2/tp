package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_PATIENT_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_FORCE_DELETE_PATIENT_REQUIRED;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.logic.commands.patient.DeletePatientCommand.FORCE_DELETE_MESSAGE_USAGE;
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
import seedu.address.model.person.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePatientCommandTest {
    // empty appointment schedule to prevent conflict in delete operations
    private Model emptyApptScheduleModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
            new AppointmentSchedule(), new UserPrefs());

    @Test
    public void execute_nonForceValidIndexUnfilteredPatientRecordsNoExistingAppointment_success() {
        Patient patientToDelete = emptyApptScheduleModel.getFilteredPatientList().get(
                INDEX_FIRST_IN_LIST.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_IN_LIST, false);

        String expectedMessage = String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(emptyApptScheduleModel.getPatientRecords()),
                new AddressBook<>(emptyApptScheduleModel.getDoctorRecords()),
                new AppointmentSchedule(emptyApptScheduleModel.getAppointmentSchedule()),
                new UserPrefs(emptyApptScheduleModel.getUserPrefs())
        );

        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deletePatientCommand, emptyApptScheduleModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonForceValidIndexUnfilteredPatientRecordsExistingAppointment_throwsCommandException() {
        Model nonEmptyApptScheduleModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_SECOND_IN_LIST, false);

        String expectedMessage = String.format(MESSAGE_FORCE_DELETE_PATIENT_REQUIRED, FORCE_DELETE_MESSAGE_USAGE);

        assertCommandFailure(deletePatientCommand, nonEmptyApptScheduleModel, expectedMessage);
    }

    @Test
    public void execute_nonForceInvalidIndexUnfilteredPatientRecordsNoExistingAppointment_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(emptyApptScheduleModel.getFilteredPatientList().size() + 1);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex, false);

        assertCommandFailure(deletePatientCommand, emptyApptScheduleModel, MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_nonForceValidIndexFilteredPatientRecordsNoExistingAppointment_success() {
        showPatientAtIndex(emptyApptScheduleModel, INDEX_FIRST_IN_LIST);

        Patient personToDelete = emptyApptScheduleModel.getFilteredPatientList().get(
                INDEX_FIRST_IN_LIST.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_IN_LIST, false);

        String expectedMessage = String.format(MESSAGE_DELETE_PATIENT_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(emptyApptScheduleModel.getPatientRecords()),
                new AddressBook<>(emptyApptScheduleModel.getDoctorRecords()),
                new AppointmentSchedule(emptyApptScheduleModel.getAppointmentSchedule()),
                new UserPrefs(emptyApptScheduleModel.getUserPrefs())
        );

        expectedModel.deletePatient(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deletePatientCommand, emptyApptScheduleModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonForceInvalidIndexFilteredPatientRecordsNoExistingAppointment_throwsCommandException() {
        showPatientAtIndex(emptyApptScheduleModel, INDEX_FIRST_IN_LIST);

        Index outOfBoundIndex = INDEX_SECOND_IN_LIST;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < emptyApptScheduleModel.getPatientRecords().getPersonList().size());

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex, false);

        assertCommandFailure(deletePatientCommand, emptyApptScheduleModel, MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_forceValidIndexUnfilteredPatientRecordsExistingAppointment_success() {
        Model nonEmptyApptScheduleModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        Patient patientToDelete = nonEmptyApptScheduleModel.getFilteredPatientList().get(
                INDEX_SECOND_IN_LIST.getZeroBased());

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_SECOND_IN_LIST, true);

        String expectedMessage = String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        Model expectedModel = new ModelManager(
                new AddressBook<>(nonEmptyApptScheduleModel.getPatientRecords()),
                new AddressBook<>(nonEmptyApptScheduleModel.getDoctorRecords()),
                new AppointmentSchedule(nonEmptyApptScheduleModel.getAppointmentSchedule()),
                new UserPrefs(nonEmptyApptScheduleModel.getUserPrefs())
        );

        expectedModel.deletePatientAppointments(patientToDelete.getUuid());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deletePatientCommand, nonEmptyApptScheduleModel, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(INDEX_FIRST_IN_LIST, false);
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(INDEX_SECOND_IN_LIST, false);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(INDEX_FIRST_IN_LIST, false);
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
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
