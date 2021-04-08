package seedu.address.logic.commands.schedulecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DATE_CLASH;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_END_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LONG_HOURS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SHORT_HOURS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_TIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_MINUTES;
import static seedu.address.commons.core.Messages.MESSAGE_TIME_FROM_GREATER_THAN;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.schedulecommands.AddScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.tutorcommands.AddCommand;
import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TutorBuilder;
import seedu.address.testutil.TypicalModel;


public class AddScheduleCommandTest {

    private final Model model = TypicalModel.getTypicalModel();
    private final Schedule firstSchedule = model.getScheduleTracker().getScheduleList().get(0);

    @Test
    public void execute_alreadyHasSchedule() {
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(firstSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_invalidTimeRangeSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeTo("2021-05-24 9:00AM")
                .withTimeFrom("2021-05-24 11:00AM").build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_TIME_FROM_GREATER_THAN);
    }

    @Test
    public void execute_pastDateSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-01-24 9:00AM")
                .withTimeTo("2021-01-24 11:00AM").build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_INVALID_DATE);
    }

    @Test
    public void execute_invalidStartTimeSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-06-24 5:30AM")
                .withTimeTo("2021-06-24 11:00AM").build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_INVALID_START_TIME);
    }

    @Test
    public void execute_invalidEndTimeSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-06-24 10:00AM")
                .withTimeTo("2021-06-24 11:30PM").build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_INVALID_END_TIME);
    }

    @Test
    public void execute_tooShortSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-06-24 10:00AM")
                .withTimeTo("2021-06-24 10:30AM").build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_INVALID_SHORT_HOURS);
    }

    @Test
    public void execute_tooLongSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-06-24 6:00AM")
                .withTimeTo("2021-06-24 11:00PM").build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_INVALID_LONG_HOURS);
    }

    @Test
    public void execute_invalidMinutesSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-06-24 6:35AM")
                .withTimeTo("2021-06-24 10:00AM").build();
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);
        assertCommandFailure(addScheduleCommand, model, MESSAGE_INVALID_TIME_MINUTES);

        invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-06-24 6:00AM")
                .withTimeTo("2021-06-24 10:05AM").build();
        addScheduleCommand = new AddScheduleCommand(invalidSchedule);
        assertCommandFailure(addScheduleCommand, model, MESSAGE_INVALID_TIME_MINUTES);
    }

    @Test
    public void execute_clashingSchedule() {
        Schedule invalidSchedule = new ScheduleBuilder(firstSchedule)
                .withTimeFrom("2021-05-24 9:00AM")
                .withTimeTo("2021-05-24 11:00AM").build();

        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(invalidSchedule);

        assertCommandFailure(addScheduleCommand, model, MESSAGE_DATE_CLASH);
    }

    @Test
    public void equals() {
        Schedule schedule1 = new ScheduleBuilder().withTitle("Maths Homework").build();
        Schedule schedule2 = new ScheduleBuilder().withTitle("Science Homework").build();
        AddScheduleCommand addSchedule1Command = new AddScheduleCommand(schedule1);
        AddScheduleCommand addSchedule2Command = new AddScheduleCommand(schedule2);

        // same object -> returns true
        assertEquals(addSchedule1Command, addSchedule1Command);

        // same values -> returns true
        AddScheduleCommand addSchedule1CommandCopy = new AddScheduleCommand(schedule1);
        assertEquals(addSchedule1CommandCopy, addSchedule1Command);

        // different types -> returns false
        assertNotEquals(addSchedule1Command, 1);

        // null -> returns false
        assertNotEquals(addSchedule1Command, null);

        // different person -> returns false
        assertNotEquals(addSchedule1Command, addSchedule2Command);
    }
}
