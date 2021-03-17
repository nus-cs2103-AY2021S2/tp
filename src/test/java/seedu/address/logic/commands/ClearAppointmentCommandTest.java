package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAppointmentSchedule;

import org.junit.jupiter.api.Test;

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
                ClearAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAppointmentSchedule_success() {
        Model model = new ModelManager(getTypicalAppointmentSchedule(), getTypicalAddressBook(),
                new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAppointmentSchedule(), getTypicalAddressBook(),
                new UserPrefs());
        expectedModel.setAppointmentSchedule(new AppointmentSchedule());

        assertCommandSuccess(new ClearAppointmentCommand(), model,
                ClearAppointmentCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
