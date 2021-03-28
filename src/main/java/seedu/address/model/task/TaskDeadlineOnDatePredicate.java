package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Predicate testing whether the task's deadline being tested falls on the deadline provided.
 */
public class TaskDeadlineOnDatePredicate implements Predicate<Task> {
    private final Deadline deadline;

    public TaskDeadlineOnDatePredicate(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Checks if deadline of this predicate is on the same date as the provided task's deadline.
     *
     * @param task Task with deadline to be checked with this predicate's deadline.
     * @return Boolean indicating whether the task's deadline is on the same date as this predicate's date.
     */
    @Override
    public boolean test(Task task) {
        return task.hasSameDeadlineDate(deadline);
    }
}
