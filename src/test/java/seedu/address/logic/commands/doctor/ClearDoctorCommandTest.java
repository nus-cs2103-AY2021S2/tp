package seedu.address.logic.commands.doctor;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearDoctorCommandTest {

    @Test
    public void execute_emptyDoctorRecords_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearDoctorCommand(), model, ClearDoctorCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyDoctorRecords_success() {
        // empty appointment schedule to prevent conflict
        Model model = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                new AppointmentSchedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPatientRecords(), getTypicalDoctorRecords(),
                new AppointmentSchedule(), new UserPrefs());

        expectedModel.setDoctorRecords(new AddressBook<>());
        assertCommandSuccess(new ClearDoctorCommand(), model, ClearDoctorCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
