package seedu.address.logic.commands.eventcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtDate;
import static seedu.address.logic.commands.CommandTestUtil.showScheduleAtDate;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_FIRST_DATE;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_SECOND_DATE;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.model.schedule.ScheduleDateViewPredicate;
import seedu.address.testutil.TypicalModel;

public class ViewEventCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = TypicalModel.getTypicalModel();
        expectedModel = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                model.getAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                model.getScheduleTracker(), getTypicalReminderTracker());
    }

    @Test
    public void execute_validDateUnfilteredList_success() {
        DateViewPredicate predicate = new DateViewPredicate(APPOINTMENT_FIRST_DATE);
        ScheduleDateViewPredicate predicate2 = new ScheduleDateViewPredicate(APPOINTMENT_FIRST_DATE);
        expectedModel.updateFilteredAppointmentList(predicate);
        expectedModel.updateFilteredScheduleList(predicate2);

        ViewEventCommand viewCommand = new ViewEventCommand(APPOINTMENT_FIRST_DATE);

        String expectedMessage = String.format(ViewEventCommand.MESSAGE_VIEW_EVENT_SUCCESS,
                APPOINTMENT_FIRST_DATE.toDateString(), expectedModel.getFilteredAppointmentList().size(),
                expectedModel.getFilteredScheduleList().size());
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateFilteredList_success() {
        showAppointmentAtDate(model, APPOINTMENT_FIRST_DATE);
        showScheduleAtDate(model, APPOINTMENT_FIRST_DATE);

        DateViewPredicate predicate = new DateViewPredicate(APPOINTMENT_FIRST_DATE);
        ScheduleDateViewPredicate predicate2 = new ScheduleDateViewPredicate(APPOINTMENT_FIRST_DATE);
        expectedModel.updateFilteredAppointmentList(predicate);
        expectedModel.updateFilteredScheduleList(predicate2);

        ViewEventCommand viewCommand = new ViewEventCommand(APPOINTMENT_FIRST_DATE);

        String expectedMessage = String.format(ViewEventCommand.MESSAGE_VIEW_EVENT_SUCCESS,
                APPOINTMENT_FIRST_DATE.toDateString(), expectedModel.getFilteredAppointmentList().size(),
                expectedModel.getFilteredScheduleList().size());
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ViewEventCommand viewFirstCommand = new ViewEventCommand(APPOINTMENT_FIRST_DATE);
        ViewEventCommand viewSecondCommand = new ViewEventCommand(APPOINTMENT_SECOND_DATE);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewEventCommand viewFirstCommandCopy = new ViewEventCommand(APPOINTMENT_FIRST_DATE);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
