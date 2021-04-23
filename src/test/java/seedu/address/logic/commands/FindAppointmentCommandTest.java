package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.BALLET_RECITAL;
import static seedu.address.testutil.TypicalAppointments.PLAY_DATE;
import static seedu.address.testutil.TypicalAppointments.PSG_MEETING;
import static seedu.address.testutil.TypicalAppointments.PTM;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.predicate.ApptNameContainsKeywordsPredicate;




/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = new ModelManager(new AddressBook(), getTypicalAppointmentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(new AddressBook(), getTypicalAppointmentBook(), new UserPrefs());

    @Test
    public void equals() {
        ApptNameContainsKeywordsPredicate firstPredicate =
                new ApptNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ApptNameContainsKeywordsPredicate secondPredicate =
                new ApptNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAppointmentCommand findAppointmentFirstCommand = new FindAppointmentCommand(firstPredicate);
        FindAppointmentCommand findAppointmentSecondCommand = new FindAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findAppointmentFirstCommand.equals(findAppointmentFirstCommand));

        // same values -> returns true
        FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(firstPredicate);
        assertTrue(findAppointmentFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findAppointmentFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findAppointmentFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(findAppointmentFirstCommand.equals(findAppointmentSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_oneKeywordPartOfName_oneAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate("ballet");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BALLET_RECITAL), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_twoKeywordsOneFullName_oneAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate("ballet recital");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BALLET_RECITAL), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_multiplePartOfNamesKeywords_multipleAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 3);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate("ballet psg play");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BALLET_RECITAL, PSG_MEETING, PLAY_DATE), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_somePartOfNamesSomeFullNamesKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 3);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate("date ballet PSG recital play");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BALLET_RECITAL, PSG_MEETING, PLAY_DATE), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_oneIncompleteKeyword_oneContactFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate("ball");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BALLET_RECITAL), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_multipleIncompleteKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 3);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate("ball mee pla");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BALLET_RECITAL, PSG_MEETING, PLAY_DATE), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_someIncompleteKeywordsSomeCompleteKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 4);
        ApptNameContainsKeywordsPredicate predicate = preparePredicate("ball psg meeting date pt");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BALLET_RECITAL, PSG_MEETING, PLAY_DATE, PTM), model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ApptNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ApptNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
