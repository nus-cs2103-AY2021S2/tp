package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.BookingContainsTagPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterBookingByTagCommand}.
 */
public class FilterBookingByTagCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        BookingContainsTagPredicate firstPredicate =
                new BookingContainsTagPredicate(VALID_TAG_FRIEND);
        BookingContainsTagPredicate secondPredicate =
                new BookingContainsTagPredicate(VALID_TAG_HUSBAND);

        FilterBookingByTagCommand findFirstCommand = new FilterBookingByTagCommand(firstPredicate);
        FilterBookingByTagCommand findSecondCommand = new FilterBookingByTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterBookingByTagCommand findFirstCommandCopy = new FilterBookingByTagCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different bookings -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_keywordNotInBookingSystem_noBookingFound() {
        String expectedMessage = Messages.MESSAGE_BOOKING_FILTER_FAILED;
        BookingContainsTagPredicate predicate = preparePredicate(VALID_TAG_FRIEND);
        FilterBookingByTagCommand command = new FilterBookingByTagCommand(predicate);
        expectedModel.updateFilteredBookingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    /**
     * Parses {@code userInput} into a {@code BookingContainsTagPredicate}.
     */
    private BookingContainsTagPredicate preparePredicate(String userInput) {
        return new BookingContainsTagPredicate(userInput);
    }
}
