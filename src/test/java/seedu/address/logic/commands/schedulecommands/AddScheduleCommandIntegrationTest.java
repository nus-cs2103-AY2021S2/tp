package seedu.address.logic.commands.schedulecommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModel.ModelType.SCHEDULETRACKER;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.ScheduleBuilder;
import seedu.address.testutil.TypicalModel;

/**
 * Contains integration tests (interaction with the Model) for {@code AddScheduleCommand}.
 */
public class AddScheduleCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = TypicalModel.getTypicalModel();
    }

    @Test
    public void execute_newSchedule_success() {
        Schedule validSchedule = new ScheduleBuilder().build();

        Model expectedModel = TypicalModel.getTypicalModel(model, SCHEDULETRACKER);

        expectedModel.addSchedule(validSchedule);

        assertCommandSuccess(new AddScheduleCommand(validSchedule), model,
                String.format(AddScheduleCommand.MESSAGE_SUCCESS, validSchedule), expectedModel);
    }

    @Test
    public void execute_duplicateSchedule_throwsCommandException() {
        Schedule scheduleInList = model.getScheduleTracker().getScheduleList().get(0);
        assertCommandFailure(new AddScheduleCommand(scheduleInList), model,
                AddScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }
}
