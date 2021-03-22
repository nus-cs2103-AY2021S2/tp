package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.MESSAGE_BOOKING_DISPLAYED;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_ID_1;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_ID_2;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.BookingIdContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindBookingCommand}.
 */
public class FindBookingCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        BookingIdContainsKeywordsPredicate firstPredicate =
                new BookingIdContainsKeywordsPredicate(VALID_BOOKING_ID_1);
        BookingIdContainsKeywordsPredicate secondPredicate =
                new BookingIdContainsKeywordsPredicate(VALID_BOOKING_ID_2);

        FindBookingCommand findFirstCommand = new FindBookingCommand(firstPredicate);
        FindBookingCommand findSecondCommand = new FindBookingCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBookingCommand findFirstCommandCopy = new FindBookingCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different bookings -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_BOOKING_DISPLAYED, 0);
        BookingIdContainsKeywordsPredicate predicate = preparePredicate("");
        FindBookingCommand command = new FindBookingCommand(predicate);
        expectedModel.updateFilteredBookingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    /**
     * Parses {@code userInput} into a {@code BookingIdContainsKeywordsPredicate}.
     */
    private BookingIdContainsKeywordsPredicate preparePredicate(String userInput) {
        return new BookingIdContainsKeywordsPredicate(userInput);
    }
}
