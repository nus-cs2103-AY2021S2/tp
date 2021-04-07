package seedu.address.logic.commands.schedulecommands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNABLE_TO_EDIT_PAST_SCHEDULE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.schedulecommands.EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalPastSchedules;
import static seedu.address.testutil.TypicalSchedules.getTypicalSchedules;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedulecommands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.testutil.TypicalModel;

public class EditScheduleCommandTest {

    private final Model model = TypicalModel.getTypicalModel();

    @Test
    public void execute_editedAppointmentAlreadyExists_failure() {
        List<Schedule> scheduleList = getTypicalSchedules();
        Schedule schedule1 = scheduleList.get(0);
        EditScheduleDescriptor editAppointmentDescriptor = new EditScheduleDescriptor();
        editAppointmentDescriptor.setTitle(schedule1.getTitle());
        editAppointmentDescriptor.setTimeTo(schedule1.getTimeTo());
        editAppointmentDescriptor.setTimeFrom(schedule1.getTimeFrom());
        editAppointmentDescriptor.setDescription(schedule1.getDescription());

        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(Index.fromOneBased(1),
                editAppointmentDescriptor);
        assertCommandFailure(editScheduleCommand, model, MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_editedPastSchedule_failure() {
        ScheduleTracker scheduleTracker = new ScheduleTracker();
        scheduleTracker.setSchedules(getTypicalPastSchedules());
        Model pastModel = new ModelManager(getTypicalTutorBook(), new UserPrefs(),
                getTypicalAppointmentBook(), getTypicalBudgetBook(), getTypicalGradeBook(),
                scheduleTracker, getTypicalReminderTracker());

        List<Schedule> scheduleList = getTypicalPastSchedules();
        Schedule schedule1 = scheduleList.get(0);
        EditScheduleDescriptor editAppointmentDescriptor = new EditScheduleDescriptor();
        editAppointmentDescriptor.setTitle(schedule1.getTitle());
        editAppointmentDescriptor.setTimeTo(schedule1.getTimeTo());
        editAppointmentDescriptor.setTimeFrom(schedule1.getTimeFrom());
        editAppointmentDescriptor.setDescription(schedule1.getDescription());

        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(Index.fromOneBased(1),
                editAppointmentDescriptor);

        assertCommandFailure(editScheduleCommand, pastModel, MESSAGE_UNABLE_TO_EDIT_PAST_SCHEDULE);
    }

    @Test
    public void execute_invalidIndexOutOfBounds_failure() {
        List<Schedule> scheduleList = getTypicalSchedules();
        Schedule schedule1 = scheduleList.get(0);
        EditScheduleDescriptor editAppointmentDescriptor = new EditScheduleDescriptor();
        editAppointmentDescriptor.setTitle(schedule1.getTitle());
        editAppointmentDescriptor.setTimeTo(schedule1.getTimeTo());
        editAppointmentDescriptor.setTimeFrom(schedule1.getTimeFrom());
        editAppointmentDescriptor.setDescription(schedule1.getDescription());

        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(Index.fromOneBased(1000000),
                editAppointmentDescriptor);

        assertCommandFailure(editScheduleCommand, model, MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }
}
