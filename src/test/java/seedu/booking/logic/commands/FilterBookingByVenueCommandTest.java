package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_VENUE1;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.BookingContainsVenuePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterBookingByVenueCommand}.
 */
public class FilterBookingByVenueCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        BookingContainsVenuePredicate firstPredicate =
                new BookingContainsVenuePredicate(VALID_VENUE_NAME_FIELD);
        BookingContainsVenuePredicate secondPredicate =
                new BookingContainsVenuePredicate(VALID_VENUE_NAME_VENUE1);

        FilterBookingByVenueCommand findFirstCommand = new FilterBookingByVenueCommand(firstPredicate);
        FilterBookingByVenueCommand findSecondCommand = new FilterBookingByVenueCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterBookingByVenueCommand findFirstCommandCopy = new FilterBookingByVenueCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different bookings -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBookingFound() {
        String expectedMessage = Messages.MESSAGE_BOOKING_FILTER_FAILED;
        BookingContainsVenuePredicate predicate = preparePredicate("");
        FilterBookingByVenueCommand command = new FilterBookingByVenueCommand(predicate);
        expectedModel.updateFilteredBookingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    /**
     * Parses {@code userInput} into a {@code BookingContainsVenuePredicate}.
     */
    private BookingContainsVenuePredicate preparePredicate(String userInput) {
        return new BookingContainsVenuePredicate(userInput);
    }
}
