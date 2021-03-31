package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_DATE_FEBRUARY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_DATE_MARCH;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.BookingWithinDatePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterBookingByDateCommand}.
 */
public class FilterBookingByDateCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        LocalDate localDateFeb = LocalDate.parse(VALID_DATE_FEBRUARY);
        LocalDate localDateMarch = LocalDate.parse(VALID_DATE_MARCH);

        BookingWithinDatePredicate firstPredicate =
                new BookingWithinDatePredicate(localDateFeb);
        BookingWithinDatePredicate secondPredicate =
                new BookingWithinDatePredicate(localDateMarch);

        FilterBookingByDateCommand findFirstCommand = new FilterBookingByDateCommand(firstPredicate);
        FilterBookingByDateCommand findSecondCommand = new FilterBookingByDateCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterBookingByDateCommand findFirstCommandCopy = new FilterBookingByDateCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different dates -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBookingFound() {
        String expectedMessage = Messages.MESSAGE_BOOKING_FILTER_FAILED + " on " + "2020-01-01";
        BookingWithinDatePredicate predicate = preparePredicate(LocalDate.parse("2020-01-01"));
        FilterBookingByDateCommand command = new FilterBookingByDateCommand(predicate);
        expectedModel.updateFilteredBookingList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    /**
     * Parses {@code userInput} into a {@code BookingWithinDatePredicate}.
     */
    private BookingWithinDatePredicate preparePredicate(LocalDate userInput) {
        return new BookingWithinDatePredicate(userInput);
    }
}
