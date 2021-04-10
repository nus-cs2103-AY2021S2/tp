package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.tag.UniqueTagListTestUtil.VALID_TAG_FRIEND;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.conditions.ConstraintManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPlanner(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() {
        Task validTask = new TaskBuilder().build();

        Model expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());
        expectedModel.addTask(validTask);

        assertCommandSuccess(new AddCommand(validTask), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTask), expectedModel);
    }

    @Test
    public void execute_taskWithDateAndRecurringSchedule_throwsCommandException() {
        Task invalidTask = new TaskBuilder().withTitle(VALID_TITLE_AMY)
                .withDate(VALID_DATE_AMY).withRecurringSchedule(VALID_RECURRINGSCHEDULE_AMY)
                .withDescription(VALID_DESCRIPTION_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();

        assertCommandFailure(new AddCommand(invalidTask), model,
                ConstraintManager.MESSAGE_DATE_RECURRING_SCHEDULE_CONFLICT);
    }

    @Test
    public void execute_taskWithDurationOnly_throwsCommandException() {
        Task invalidTask = new TaskBuilder().withTitle(VALID_TITLE_AMY).withDuration(VALID_DURATION_AMY).build();

        assertCommandFailure(new AddCommand(invalidTask), model, ConstraintManager.MESSAGE_DURATION_STANDALONE_ERROR);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getPlanner().getTaskList().get(0);
        assertCommandFailure(new AddCommand(taskInList), model, AddCommand.MESSAGE_DUPLICATE_TASK);
    }

}
