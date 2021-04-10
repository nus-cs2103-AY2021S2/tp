package seedu.address.logic.commands.schedulecommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showScheduleAtDate;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_FIRST_DATE;
import static seedu.address.testutil.TypicalDates.APPOINTMENT_SECOND_DATE;
import static seedu.address.testutil.TypicalModel.ModelType.SCHEDULETRACKER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDateViewPredicate;
import seedu.address.testutil.TypicalModel;

public class ViewScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = TypicalModel.getTypicalModel();
        expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);
    }

    @Test
    public void execute_validDateUnfilteredList_success() {
        ScheduleDateViewPredicate predicate = new ScheduleDateViewPredicate(APPOINTMENT_FIRST_DATE);
        Schedule scheduleToView = model.getFilteredScheduleList().filtered(predicate).get(0);
        ViewScheduleCommand viewCommand = new ViewScheduleCommand(predicate);
        expectedModel.updateFilteredScheduleList(predicate);

        String expectedMessage = String.format(ViewScheduleCommand.MESSAGE_VIEW_SCHEDULE_SUCCESS,
                scheduleToView.getTimeFrom().toDateString(), expectedModel.getFilteredScheduleList().size());
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateFilteredList_success() {
        showScheduleAtDate(model, APPOINTMENT_FIRST_DATE);

        ScheduleDateViewPredicate predicate = new ScheduleDateViewPredicate(APPOINTMENT_FIRST_DATE);
        Schedule scheduleToView = model.getFilteredScheduleList().filtered(predicate).get(0);
        ViewScheduleCommand viewCommand = new ViewScheduleCommand(predicate);
        expectedModel.updateFilteredScheduleList(predicate);

        String expectedMessage = String.format(ViewScheduleCommand.MESSAGE_VIEW_SCHEDULE_SUCCESS,
                scheduleToView.getTimeFrom().toDateString(), expectedModel.getFilteredScheduleList().size());

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ScheduleDateViewPredicate firstPredicate = new ScheduleDateViewPredicate(APPOINTMENT_FIRST_DATE);
        ScheduleDateViewPredicate secondPredicate = new ScheduleDateViewPredicate(APPOINTMENT_SECOND_DATE);

        ViewScheduleCommand viewFirstCommand = new ViewScheduleCommand(firstPredicate);
        ViewScheduleCommand viewSecondCommand = new ViewScheduleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewScheduleCommand viewFirstCommandCopy = new ViewScheduleCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
