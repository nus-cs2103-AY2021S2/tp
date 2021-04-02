package seedu.booking.logic.commands;

import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalBookings.getTypicalBookingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListBookingCommand.
 */
public class ListBookingCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
        expectedModel = new ModelManager(model.getBookingSystem(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListBookingCommand(), model,
                ListBookingCommand.MESSAGE_BOOKING_LISTED_SUCCESS, expectedModel);
    }

    /*@Test
    public void execute_listIsFiltered_showsEverything() {
        showBookingAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListBookingCommand(), model,
                ListBookingCommand.MESSAGE_BOOKING_LISTED_SUCCESS, expectedModel);
    }*/
}
