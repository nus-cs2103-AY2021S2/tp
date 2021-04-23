package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListAppointmentCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListAppointmentCommandTest {
    private Model model = new ModelManager(new AddressBook(), getTypicalAppointmentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(new AddressBook(), getTypicalAppointmentBook(), new UserPrefs());

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentCommand(), model, MESSAGE_SUCCESS, expectedModel);
    }
}
