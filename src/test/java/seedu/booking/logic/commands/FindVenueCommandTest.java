package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.MESSAGE_VENUE_DISPLAYED;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_VENUES;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.venue.CapacityMatchesKeywordPredicate;
import seedu.booking.model.venue.Venue;

/**
 * Contains integration tests (interaction with the Model) for {@code FindVenueCommand}.
 */
public class FindVenueCommandTest {
    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void equals() {
        List<Predicate<Venue>> firstPredicateList = new ArrayList<>();
        CapacityMatchesKeywordPredicate capacityPredicateField =
                new CapacityMatchesKeywordPredicate(VALID_VENUE_CAPACITY_FIELD);
        firstPredicateList.add(capacityPredicateField);

        List<Predicate<Venue>> secondPredicateList = new ArrayList<>();
        CapacityMatchesKeywordPredicate capacityPredicateHall =
                new CapacityMatchesKeywordPredicate(VALID_VENUE_CAPACITY_HALL);
        secondPredicateList.add(capacityPredicateHall);

        FindVenueCommand findFirstCommand = new FindVenueCommand(firstPredicateList);
        FindVenueCommand findSecondCommand = new FindVenueCommand(secondPredicateList);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindVenueCommand findFirstCommandCopy = new FindVenueCommand(firstPredicateList);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different venues -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_noCapacityKeywordMatches_noVenueFound() {
        String expectedMessage = String.format(MESSAGE_VENUE_DISPLAYED, 0);

        List<Predicate<Venue>> predicate = prepareCapacityPredicate(999);

        FindVenueCommand command = new FindVenueCommand(predicate);
        expectedModel.updateFilteredVenueList(combineVenuePredicates(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVenueList());
    }

    /*@Test
    public void execute_capacityKeywordMatches_oneVenueFound() {
        String expectedMessage = String.format(MESSAGE_VENUE_DISPLAYED, 1);
        List<Predicate<Venue>> predicate = prepareCapacityPredicate(50);

        FindVenueCommand command = new FindVenueCommand(predicate);
        expectedModel.updateFilteredVenueList(combineVenuePredicates(predicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }*/

    /**
     * Parses {@code capacityKeyword} into a {@code List<Predicate<Venue>>}.
     */
    private List<Predicate<Venue>> prepareCapacityPredicate(int capacityKeyword) {
        List<Predicate<Venue>> firstPredicateList = new ArrayList<>();
        CapacityMatchesKeywordPredicate capacityPredicate = new CapacityMatchesKeywordPredicate(capacityKeyword);
        firstPredicateList.add(capacityPredicate);
        return firstPredicateList;
    }

    private static Predicate<Venue> combineVenuePredicates(List<Predicate<Venue>> predicateList) {
        return predicateList.stream().reduce(Predicate::and).orElse(PREDICATE_SHOW_ALL_VENUES);
    }
}
