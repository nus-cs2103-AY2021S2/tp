package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PASSENGER_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRST_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FIRST_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_STR_FRIDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_STR_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_STR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_STR_MORNING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPassengers.ALICE;
import static seedu.address.testutil.TypicalPassengers.BENSON;
import static seedu.address.testutil.TypicalPassengers.CARL;
import static seedu.address.testutil.TypicalPassengers.DANIEL;
import static seedu.address.testutil.TypicalPassengers.ELLE;
import static seedu.address.testutil.TypicalPassengers.FIONA;
import static seedu.address.testutil.TypicalPassengers.GEORGE;
import static seedu.address.testutil.TypicalPassengers.HILARY;
import static seedu.address.testutil.TypicalPassengers.IRENE;
import static seedu.address.testutil.TypicalPassengers.JACKSON;
import static seedu.address.testutil.TypicalPassengers.KINGSLEY;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBookPassengers;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TripDayContainsKeywordsPredicate;
import seedu.address.model.TripTimeContainsKeywordsPredicate;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.passenger.AddressContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookPassengers(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookPassengers(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList(VALID_FIRST_NAME_AMY));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList(VALID_FIRST_NAME_BOB));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different passenger -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPassengerFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPassengerList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePassengersFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz".toLowerCase());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPassengerList());
    }

    @Test
    public void execute_onePhoneKeyword_onePassengersFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 1);
        PhoneContainsKeywordsPredicate predicate = preparePhonePredicate(ALICE.getPhone().toString());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPassengerList());
    }

    @Test
    public void execute_oneAddressKeyword_onePassengersFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 3);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate("street");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, GEORGE), model.getFilteredPassengerList());
    }

    @Test
    public void execute_oneTripDayKeyword_onePassengersFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 5);
        TripDayContainsKeywordsPredicate predicate = prepareTripDayPredicate(VALID_TRIPDAY_STR_MONDAY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, HILARY, IRENE, JACKSON, KINGSLEY), model.getFilteredPassengerList());
    }

    @Test
    public void execute_multipleTripDayKeyword_sixPassengersFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 10);
        TripDayContainsKeywordsPredicate predicate =
                prepareTripDayPredicate(VALID_TRIPDAY_STR_MONDAY + " " + VALID_TRIPDAY_STR_FRIDAY);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, HILARY, IRENE, JACKSON, KINGSLEY),
                model.getFilteredPassengerList());
    }

    @Test
    public void execute_oneTripTimeKeyword_onePassengersFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 1);
        TripTimeContainsKeywordsPredicate predicate = prepareTripTimePredicate(VALID_TRIPTIME_STR_MORNING);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPassengerList());
    }

    @Test
    public void execute_multipleTripTimeKeyword_fivePassengersFound() {
        String expectedMessage = String.format(MESSAGE_PASSENGER_LISTED_OVERVIEW, 5);
        TripTimeContainsKeywordsPredicate predicate =
                prepareTripTimePredicate(VALID_TRIPTIME_STR_MORNING + " " + VALID_TRIPTIME_STR_BOB);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPassengerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, HILARY, IRENE, JACKSON, KINGSLEY), model.getFilteredPassengerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private PhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private TripDayContainsKeywordsPredicate prepareTripDayPredicate(String userInput) {
        return new TripDayContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private TripTimeContainsKeywordsPredicate prepareTripTimePredicate(String userInput) {
        return new TripTimeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
