package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.clear.ClearAppointmentCommand;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearAppointmentCommandTest {

    @Test
    public void execute_emptyAppointmentBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearAppointmentCommand(), model, ClearAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyAppointmentBook_success() {
        Model model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
        expectedModel.setAppointmentBook(new AppointmentBook());

        assertCommandSuccess(new ClearAppointmentCommand(), model, ClearAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

}
