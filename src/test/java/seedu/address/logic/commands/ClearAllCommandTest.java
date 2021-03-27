package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;

public class ClearAllCommandTest {

    @Test
    public void execute_emptyAppointmentBook_success() {
        Model model = new ModelManager(new AppointmentBook(), new PropertyBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AppointmentBook(), new PropertyBook(), new UserPrefs());

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyAppointmentBook_success() {
        Model model = new ModelManager(getTypicalAppointmentBook(), new PropertyBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new PropertyBook(), new UserPrefs());
        expectedModel.setAppointmentBook(new AppointmentBook());

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_emptyPropertyBook_success() {
        Model model = new ModelManager(new AppointmentBook(), new PropertyBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AppointmentBook(), new PropertyBook(), new UserPrefs());

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyBook_success() {
        Model model = new ModelManager(new AppointmentBook(), getTypicalPropertyBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AppointmentBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel.setPropertyBook(new PropertyBook());

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

}
