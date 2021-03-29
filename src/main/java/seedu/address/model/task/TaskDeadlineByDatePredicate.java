package seedu.address.model.task;

import java.util.function.Predicate;

import seedu.address.model.common.Date;

/**
 * Tests that a {@code Task}'s {@code deadline} is before or on the by date given.
 */
public class TaskDeadlineByDatePredicate implements Predicate<Task> {
    private final Date byDate;

    public TaskDeadlineByDatePredicate(Date byDate) {
        this.byDate = byDate;
    }

    @Override
    public boolean test(Task task) {
        Date taskDeadline = task.getDeadline();
        return taskDeadline.isBefore(byDate) || taskDeadline.isEqual(byDate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDeadlineByDatePredicate)) {
            return false;
        }

        TaskDeadlineByDatePredicate t = (TaskDeadlineByDatePredicate) other;
        return byDate.equals(t.byDate); // state check
    }
}
