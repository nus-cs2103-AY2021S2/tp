package seedu.address.logic.commands.patient;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppObjects.getTypicalAppointmentSchedule;
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

        assertCommandSuccess(new ClearPatientCommand(), model, ClearPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPatientRecords_success() {
        Model model = new ModelManager(getTypicalAppointmentSchedule(), getTypicalPatientRecords(),
                new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAppointmentSchedule(), getTypicalPatientRecords(),
                new UserPrefs());
        expectedModel.setPatientRecords(new AddressBook<>());

        assertCommandSuccess(new ClearPatientCommand(), model, ClearPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
