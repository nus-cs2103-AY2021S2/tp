package seedu.address.logic.commands.appointment;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearAppointmentCommandTest {

    @Test
    public void execute_emptyAppointmentSchedule_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearAppointmentCommand(), model,
                Messages.MESSAGE_CLEAR_APPOINTMENT_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAppointmentSchedule_success() {
        Model model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                getTypicalAppointmentSchedule(), new UserPrefs());

        expectedModel.setAppointmentSchedule(new AppointmentSchedule());
        assertCommandSuccess(new ClearAppointmentCommand(), model,
                Messages.MESSAGE_CLEAR_APPOINTMENT_SUCCESS, expectedModel);
    }

}
