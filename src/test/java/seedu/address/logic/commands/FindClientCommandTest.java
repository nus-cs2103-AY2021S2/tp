package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyClientNamePredicate;
import seedu.address.testutil.TypicalModelManager;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAppointmentCommand}.
 */
public class FindClientCommandTest {
    private Model model = TypicalModelManager.getTypicalModelManager();
    private Model expectedModel = TypicalModelManager.getTypicalModelManager();

    @Test
    public void equals() {
        PropertyClientNamePredicate firstPredicate =
                new PropertyClientNamePredicate(Collections.singletonList("first"));
        AppointmentContainsKeywordsPredicate firstPredicateAppt =
                new AppointmentContainsKeywordsPredicate(Collections.singletonList("first"));
        PropertyClientNamePredicate secondPredicate =
                new PropertyClientNamePredicate(Collections.singletonList("second"));
        AppointmentContainsKeywordsPredicate secondPredicateAppt =
                new AppointmentContainsKeywordsPredicate(Collections.singletonList("second"));

        FindClientCommand findFirstCommand = new FindClientCommand(firstPredicate, firstPredicateAppt);
        FindClientCommand findSecondCommand = new FindClientCommand(secondPredicate, secondPredicateAppt);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindClientCommand findFirstCommandCopy = new FindClientCommand(firstPredicate, firstPredicateAppt);
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
