package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;
import seedu.address.model.property.PropertyClientNamePredicate;
import seedu.address.testutil.TypicalModelManager;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.getClientFindSuccessMessage;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.MEET_BOB;
import static seedu.address.testutil.TypicalProperties.JURONG;

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
        AppointmentContainsKeywordsPredicate predicate = preparePredicateAppointment(" ");
        PropertyClientNamePredicate predicate2 = preparePredicateProperty(" ");
        FindClientCommand command = new FindClientCommand(predicate2, predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        expectedModel.updateFilteredPropertyList(predicate2);
        assertCommandSuccess(command, model, getClientFindSuccessMessage(0, 0), expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void keywordSuccessTest() {
        AppointmentContainsKeywordsPredicate predicate = preparePredicateAppointment("bob");
        PropertyClientNamePredicate predicate2 = preparePredicateProperty("bob");
        FindClientCommand command = new FindClientCommand(predicate2, predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        expectedModel.updateFilteredPropertyList(predicate2);
        assertCommandSuccess(command, model, getClientFindSuccessMessage(1, 1), expectedModel);
        assertEquals(Collections.singletonList(MEET_BOB), model.getFilteredAppointmentList());
        assertEquals(Collections.singletonList(JURONG), model.getFilteredPropertyList());
    }

    @Test
    public void unequalSuccessTest() {
        AppointmentContainsKeywordsPredicate predicate = preparePredicateAppointment("alex");
        PropertyClientNamePredicate predicate2 = preparePredicateProperty("alex");
        FindClientCommand command = new FindClientCommand(predicate2, predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        expectedModel.updateFilteredPropertyList(predicate2);
        assertCommandSuccess(command, model, getClientFindSuccessMessage(0, 1), expectedModel);
        assertEquals(Collections.singletonList(MEET_ALEX), model.getFilteredAppointmentList());
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void keywordFailTest() {
        AppointmentContainsKeywordsPredicate predicate = preparePredicateAppointment("no one");
        PropertyClientNamePredicate predicate2 = preparePredicateProperty("no one");
        FindClientCommand command = new FindClientCommand(predicate2, predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        expectedModel.updateFilteredPropertyList(predicate2);
        assertCommandSuccess(command, model, getClientFindSuccessMessage(0, 0), expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentContainsKeywordsPredicate}.
     */
    private AppointmentContainsKeywordsPredicate preparePredicateAppointment(String userInput) {
        return new AppointmentContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PropertyClientNamePredicate}.
     */
    private PropertyClientNamePredicate preparePredicateProperty(String userInput) {
        return new PropertyClientNamePredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
