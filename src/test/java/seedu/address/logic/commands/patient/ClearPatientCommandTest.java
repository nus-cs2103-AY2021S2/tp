package seedu.address.logic.commands.patient;

import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_PATIENT_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.getEmptyAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearPatientCommandTest {

    @Test
    public void execute_emptyPatientRecords_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPatientCommand(), model, MESSAGE_CLEAR_PATIENT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyAppointmentScheduleNonEmptyPatientRecords_success() {
        Model model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getEmptyAppointmentSchedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getEmptyAppointmentSchedule(), new UserPrefs());

        expectedModel.setPatientRecords(new AddressBook<>());
        assertCommandSuccess(new ClearPatientCommand(), model, MESSAGE_CLEAR_PATIENT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAppointmentScheduleNonEmptyPatientRecords_failure() {
        Model model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        assertCommandFailure(new ClearPatientCommand(), model, MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED);
    }

}
