package seedu.address.logic.conditions;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class ConstraintManagerTest {

    @Test
    public void check_durationNoDateOrRecurringSchedule_throwsCommandException() {
        Task task = new TaskBuilder().withTitle(VALID_TITLE_AMY).withDuration(VALID_DURATION_AMY).build();
        ConstraintManager constraintManager = new ConstraintManager(task);

        assertThrows(CommandException.class, () -> constraintManager.enforceAttributeConstraints());
    }

    @Test
    public void check_dateAndRecurringSchedule_throwsCommandException() {
        Task task = new TaskBuilder().withTitle(VALID_TITLE_AMY).withDate(VALID_DATE_AMY)
                .withRecurringSchedule(VALID_RECURRINGSCHEDULE_AMY).build();
        ConstraintManager constraintManager = new ConstraintManager(task);

        assertThrows(CommandException.class, () -> constraintManager.enforceAttributeConstraints());
    }
}
