package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.parseAppointmentDate;
import static seedu.address.logic.parser.ParserUtil.parseAppointmentTime;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.MEET_BOB;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentNamePredicate;
import seedu.address.model.appointment.AppointmentDatePredicate;
import seedu.address.model.appointment.AppointmentPredicateList;
import seedu.address.model.appointment.AppointmentRemarksPredicate;
import seedu.address.model.appointment.AppointmentTimePredicate;
import seedu.address.testutil.TypicalModelManager;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAppointmentCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = TypicalModelManager.getTypicalModelManager();
    private Model expectedModel = TypicalModelManager.getTypicalModelManager();

    @Test
    public void equals() {
        AppointmentNamePredicate firstPredicate =
                new AppointmentNamePredicate(Collections.singletonList("first"));
        AppointmentNamePredicate secondPredicate =
                new AppointmentNamePredicate(Collections.singletonList("second"));

        FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(firstPredicate)));
        FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(secondPredicate)));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(firstPredicate)));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneResult() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 1);
        AppointmentNamePredicate predicate =
                new AppointmentNamePredicate(Collections.singletonList("bob"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MEET_BOB), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_noResult() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 0);
        AppointmentNamePredicate predicate =
                new AppointmentNamePredicate(Collections.singletonList("someone"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_multipleResults() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW, getTypicalAppointments().size());
        AppointmentNamePredicate predicate =
                new AppointmentNamePredicate(Collections.singletonList("meet"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalAppointments(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_multipleKeywordsSuccess() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 1);
        AppointmentNamePredicate predicate =
                new AppointmentNamePredicate(Arrays.asList("bob", "bob"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MEET_BOB), model.getFilteredAppointmentList());
    }

    @Test
    public void remarksTest() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 1);
        String remark = "Bring him around Bishan to look at the properties";
        AppointmentRemarksPredicate predicate = new AppointmentRemarksPredicate(remark);
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MEET_ALEX), model.getFilteredAppointmentList());
    }

    @Test
    public void remarksFailTest() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 0);
        String remark = "some remarks here";
        AppointmentRemarksPredicate predicate = new AppointmentRemarksPredicate(remark);
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void dateTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 1);
        AppointmentDatePredicate predicate = new AppointmentDatePredicate(parseAppointmentDate("25-12-2021"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MEET_ALEX), model.getFilteredAppointmentList());
    }

    @Test
    public void dateNoneTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 0);
        AppointmentDatePredicate predicate = new AppointmentDatePredicate(parseAppointmentDate("25-01-2040"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void timeTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 1);
        AppointmentTimePredicate predicate = new AppointmentTimePredicate(parseAppointmentTime("1500"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MEET_ALEX), model.getFilteredAppointmentList());
    }

    @Test
    public void timeNoneTest() throws ParseException {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR, 0);
        AppointmentTimePredicate predicate = new AppointmentTimePredicate(parseAppointmentTime("0100"));
        FindAppointmentCommand command = new FindAppointmentCommand(
                new AppointmentPredicateList(Collections.singletonList(predicate)));
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }
}
