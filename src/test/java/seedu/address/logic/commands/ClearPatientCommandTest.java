package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.AppointmentScheduleBuilder;

public class ClearPatientCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPatientCommand(), model, ClearPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(new AppointmentScheduleBuilder().build(), getTypicalAddressBook(),
                new UserPrefs());
        Model expectedModel = new ModelManager(new AppointmentScheduleBuilder().build(), getTypicalAddressBook(),
                new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearPatientCommand(), model, ClearPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
