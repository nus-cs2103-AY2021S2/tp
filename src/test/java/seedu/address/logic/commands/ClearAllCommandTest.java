package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;

public class ClearAllCommandTest {

    @Test
    public void execute_emptyAppointmentBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearAppointmentCommand(), model, ClearAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyAppointmentBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAppointmentBook(new AppointmentBook());

        assertCommandSuccess(new ClearAppointmentCommand(), model, ClearAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_emptyPropertyBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPropertyCommand(), model, ClearPropertyCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPropertyBook(new PropertyBook());

        assertCommandSuccess(new ClearPropertyCommand(), model, ClearPropertyCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

}
