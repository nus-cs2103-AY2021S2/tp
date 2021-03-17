package seedu.booking.logic.commands;

import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.Test;

import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyBookingSystem_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
        expectedModel.setBookingSystem(new BookingSystem());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
