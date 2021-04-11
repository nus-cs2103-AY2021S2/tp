package seedu.booking.logic.commands;

import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.logic.commands.CommandTestUtil.showVenueAtIndex;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.booking.testutil.TypicalVenues.getTypicalBookingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListVenueCommand.
 */
public class ListVenueCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
        expectedModel = new ModelManager(model.getBookingSystem(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListVenueCommand(), model,
                ListVenueCommand.MESSAGE_VENUE_LISTED_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showVenueAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListVenueCommand(), model,
                ListVenueCommand.MESSAGE_VENUE_LISTED_SUCCESS, expectedModel);
    }

}
