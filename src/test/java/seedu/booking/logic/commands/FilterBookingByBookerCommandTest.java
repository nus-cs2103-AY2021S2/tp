package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.NON_EXISTENT_EMAIL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.BookingContainsBookerPredicate;
import seedu.booking.model.person.Email;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterBookingByBookerCommand}.
 */
public class FilterBookingByBookerCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        BookingContainsBookerPredicate firstPredicate =
                new BookingContainsBookerPredicate(new Email(VALID_EMAIL_AMY));
        BookingContainsBookerPredicate secondPredicate =
                new BookingContainsBookerPredicate(new Email(VALID_EMAIL_BOB));

        FilterBookingByBookerCommand findFirstCommand = new FilterBookingByBookerCommand(firstPredicate);
        FilterBookingByBookerCommand findSecondCommand = new FilterBookingByBookerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterBookingByBookerCommand findFirstCommandCopy = new FilterBookingByBookerCommand(firstPredicate);
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
        String expectedMessage = Messages.MESSAGE_BOOKING_FILTER_FAILED + " by " + NON_EXISTENT_EMAIL;
        BookingContainsBookerPredicate predicate = preparePredicate(new Email(NON_EXISTENT_EMAIL));
        FilterBookingByBookerCommand command = new FilterBookingByBookerCommand(predicate);
        expectedModel.updateFilteredBookingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    /**
     * Parses {@code userInput} into a {@code BookingContainsBookerPredicate}.
     */
    private BookingContainsBookerPredicate preparePredicate(Email userInput) {
        return new BookingContainsBookerPredicate(userInput);
    }
}
