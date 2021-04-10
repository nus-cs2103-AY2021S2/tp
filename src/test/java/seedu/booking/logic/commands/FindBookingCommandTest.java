package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.MESSAGE_NO_BOOKINGS_FOUND;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.BookerMatchesKeywordPredicate;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Email;

/**
 * Contains integration tests (interaction with the Model) for {@code FindBookingCommand}.
 */
public class FindBookingCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        List<Predicate<Booking>> firstPredicateList = new ArrayList<>();
        BookerMatchesKeywordPredicate emailPredicateAmy =
                new BookerMatchesKeywordPredicate(new Email(VALID_EMAIL_AMY));
        firstPredicateList.add(emailPredicateAmy);

        List<Predicate<Booking>> secondPredicateList = new ArrayList<>();
        BookerMatchesKeywordPredicate emailPredicateBob =
                new BookerMatchesKeywordPredicate(new Email(VALID_EMAIL_BOB));
        secondPredicateList.add(emailPredicateBob);

        FindBookingCommand findFirstCommand = new FindBookingCommand(firstPredicateList);
        FindBookingCommand findSecondCommand = new FindBookingCommand(secondPredicateList);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBookingCommand findFirstCommandCopy = new FindBookingCommand(firstPredicateList);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different bookings -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBookingsFound() {
        String expectedMessage = String.format(MESSAGE_NO_BOOKINGS_FOUND);

        List<Predicate<Booking>> predicate = prepareEmailPredicate(VALID_EMAIL_AMY);

        FindBookingCommand command = new FindBookingCommand(predicate);
        expectedModel.updateFilteredBookingList(combineVenuePredicates(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookingList());
    }

    /*@Test
    public void execute_matchingEmailKeyword_oneBookingFound() {
        String expectedMessage = String.format(MESSAGE_BOOKING_DISPLAYED, 1);
        List<Predicate<Booking>> predicate = prepareEmailPredicate(VALID_EMAIL_AMY_GMAIL);
        FindBookingCommand command = new FindBookingCommand(predicate);

        expectedModel.updateFilteredBookingList(combineVenuePredicates(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }*/

    /**
     * Parses {@code userInput} into a {@code List<Predicate<Booking>>}.
     */
    private List<Predicate<Booking>> prepareEmailPredicate(String userInput) {
        List<Predicate<Booking>> firstPredicateList = new ArrayList<>();
        Email emailKeyword = new Email(userInput);
        BookerMatchesKeywordPredicate emailPredicate = new BookerMatchesKeywordPredicate(emailKeyword);
        firstPredicateList.add(emailPredicate);
        return firstPredicateList;
    }


    private static Predicate<Booking> combineVenuePredicates(List<Predicate<Booking>> predicateList) {
        return predicateList.stream().reduce(Predicate::and).orElse(PREDICATE_SHOW_ALL_BOOKINGS);
    }
}
