package seedu.address.logic.commands.patient;

import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.getEmptyAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getEmptyDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getEmptyPatientRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearPatientCommandTest {

    @Test
    public void execute_emptyPatientRecordsEmptyAppointmentSchedule_success() {
        Model emptyDoctorRecordsModel = new ModelManager();
        Model expectedEmptyDoctorRecordsModel = new ModelManager();

        Model nonEmptyDoctorRecordsModel = new ModelManager();
        nonEmptyDoctorRecordsModel.setDoctorRecords(getTypicalDoctorRecords());

        Model expectedNonEmptyDoctorRecordsModel = new ModelManager();
        expectedNonEmptyDoctorRecordsModel.setDoctorRecords(getTypicalDoctorRecords());

        assertCommandSuccess(new ClearPatientCommand(), emptyDoctorRecordsModel,
                Messages.MESSAGE_CLEAR_PATIENT_SUCCESS, expectedEmptyDoctorRecordsModel);
        assertCommandSuccess(new ClearPatientCommand(), nonEmptyDoctorRecordsModel,
                Messages.MESSAGE_CLEAR_PATIENT_SUCCESS, expectedNonEmptyDoctorRecordsModel);
    }

    @Test
    public void execute_nonEmptyPatientRecordsEmptyAppointmentSchedule_success() {
        Model emptyDoctorRecordsModel = new ModelManager(getTypicalPatientRecords(), getEmptyDoctorRecords(),
                getEmptyAppointmentSchedule(), new UserPrefs());
        Model expectedEmptyDoctorRecordsModel = new ModelManager(getTypicalPatientRecords(),
                getEmptyDoctorRecords(), getEmptyAppointmentSchedule(), new UserPrefs());

        expectedEmptyDoctorRecordsModel.setPatientRecords(getEmptyPatientRecords());

        Model nonEmptyDoctorRecordsModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getEmptyAppointmentSchedule(), new UserPrefs());
        Model expectedNonEmptyDoctorRecordsModel = new ModelManager(getTypicalPatientRecords(),
                getTypicalDoctorRecords(), getEmptyAppointmentSchedule(), new UserPrefs());

        expectedNonEmptyDoctorRecordsModel.setPatientRecords(getEmptyPatientRecords());

        assertCommandSuccess(new ClearPatientCommand(), emptyDoctorRecordsModel,
                Messages.MESSAGE_CLEAR_PATIENT_SUCCESS, expectedEmptyDoctorRecordsModel);
        assertCommandSuccess(new ClearPatientCommand(), nonEmptyDoctorRecordsModel,
                Messages.MESSAGE_CLEAR_PATIENT_SUCCESS, expectedNonEmptyDoctorRecordsModel);
    }

    @Test
    public void execute_nonEmptyAppointmentScheduleNonEmptyPatientRecords_failure() {
        Model emptyDoctorRecordsModel = new ModelManager(getTypicalPatientRecords(), getEmptyDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        Model nonEmptyDoctorRecordsModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());
        
        assertCommandFailure(new ClearPatientCommand(), emptyDoctorRecordsModel,
                MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED);
        assertCommandFailure(new ClearPatientCommand(), nonEmptyDoctorRecordsModel,
                MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED);
    }

}
