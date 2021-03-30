package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.model.common.Date;

/**
 * Tests that a {@code Task}'s {@code deadline} is before or on the by date given and is uncompleted.
 */
public class TaskFindSchedulePredicate implements Predicate<Task> {
    private final Date byDate;

    public TaskFindSchedulePredicate(Date byDate) {
        this.byDate = byDate;
    }

    @Override
    public boolean test(Task task) {
        return isBeforeOrOnByDate(task) && isUncompleted(task);
    }

    private boolean isBeforeOrOnByDate(Task task) {
        Date taskDeadline = task.getDeadline();
        return taskDeadline.isBefore(byDate) || taskDeadline.isEqual(byDate);
    }

    private boolean isUncompleted(Task task) {
        return !task.isComplete();
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskFindSchedulePredicate)) {
            return false;
        }

        TaskFindSchedulePredicate t = (TaskFindSchedulePredicate) other;
        return byDate.equals(t.byDate); // state check
    }
}
