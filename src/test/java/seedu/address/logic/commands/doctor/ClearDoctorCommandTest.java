package seedu.address.logic.commands.doctor;

import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_APPOINTMENTS_BEFORE_DOCTORS_REQUIRED;
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

public class ClearDoctorCommandTest {

    @Test
    public void execute_emptyDoctorRecordsEmptyAppointmentSchedule_success() {
        Model emptyPatientRecordsModel = new ModelManager();
        Model expectedEmptyPatientRecordsModel = new ModelManager();

        Model nonEmptyPatientRecordsModel = new ModelManager();
        nonEmptyPatientRecordsModel.setPatientRecords(getTypicalPatientRecords());

        Model expectedNonEmptyPatientRecordsModel = new ModelManager();
        expectedNonEmptyPatientRecordsModel.setPatientRecords(getTypicalPatientRecords());

        assertCommandSuccess(new ClearDoctorCommand(), emptyPatientRecordsModel,
                Messages.MESSAGE_CLEAR_DOCTOR_SUCCESS, expectedEmptyPatientRecordsModel);
        assertCommandSuccess(new ClearDoctorCommand(), nonEmptyPatientRecordsModel,
                Messages.MESSAGE_CLEAR_DOCTOR_SUCCESS, expectedNonEmptyPatientRecordsModel);
    }

    @Test
    public void execute_nonEmptyDoctorRecordsEmptyAppointmentSchedule_success() {
        Model emptyPatientRecordsModel = new ModelManager(getEmptyPatientRecords(), getTypicalDoctorRecords(),
                getEmptyAppointmentSchedule(), new UserPrefs());
        Model expectedEmptyPatientRecordsModel = new ModelManager(getEmptyPatientRecords(),
                getTypicalDoctorRecords(), getEmptyAppointmentSchedule(), new UserPrefs());

        expectedEmptyPatientRecordsModel.setDoctorRecords(getEmptyDoctorRecords());

        Model nonEmptyPatientRecordsModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getEmptyAppointmentSchedule(), new UserPrefs());
        Model expectedNonEmptyPatientRecordsModel = new ModelManager(getTypicalPatientRecords(),
                getTypicalDoctorRecords(), getEmptyAppointmentSchedule(), new UserPrefs());

        expectedNonEmptyPatientRecordsModel.setDoctorRecords(getEmptyDoctorRecords());

        assertCommandSuccess(new ClearDoctorCommand(), emptyPatientRecordsModel,
                Messages.MESSAGE_CLEAR_DOCTOR_SUCCESS, expectedEmptyPatientRecordsModel);
        assertCommandSuccess(new ClearDoctorCommand(), nonEmptyPatientRecordsModel,
                Messages.MESSAGE_CLEAR_DOCTOR_SUCCESS, expectedNonEmptyPatientRecordsModel);
    }

    @Test
    public void execute_nonEmptyDoctorRecordsNonEmptyAppointmentSchedule_failure() {
        Model emptyPatientRecordsModel = new ModelManager(getEmptyPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        Model nonEmptyPatientRecordsModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        assertCommandFailure(new ClearDoctorCommand(), emptyPatientRecordsModel,
                MESSAGE_CLEAR_APPOINTMENTS_BEFORE_DOCTORS_REQUIRED);
        assertCommandFailure(new ClearDoctorCommand(), nonEmptyPatientRecordsModel,
                MESSAGE_CLEAR_APPOINTMENTS_BEFORE_DOCTORS_REQUIRED);
    }

}
