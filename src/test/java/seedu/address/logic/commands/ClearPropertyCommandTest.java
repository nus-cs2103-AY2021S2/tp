package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class ClearPropertyCommandTest {
    @Test
    public void execute_emptyPropertyBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearAppointmentCommand(), model, ClearAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearAppointmentCommand(), model, ClearAppointmentCommand.MESSAGE_SUCCESS,
                expectedModel);
    }
}