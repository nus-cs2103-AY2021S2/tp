package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAppointmentCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentContainsKeywordsPredicate firstPredicate =
                new AppointmentContainsKeywordsPredicate(Collections.singletonList("first"));
        AppointmentContainsKeywordsPredicate secondPredicate =
                new AppointmentContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(firstPredicate);
        FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 0);
        AppointmentContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentContainsKeywordsPredicate}.
     */
    private AppointmentContainsKeywordsPredicate preparePredicate(String userInput) {
        return new AppointmentContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
