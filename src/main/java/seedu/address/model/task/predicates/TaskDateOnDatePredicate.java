package seedu.address.model.task.predicates;

import java.util.function.Predicate;

import seedu.address.model.task.Date;
import seedu.address.model.task.Task;

/**
 * Predicate testing whether the task's date being tested falls on the date provided.
 */
class TaskDateOnDatePredicate implements Predicate<Task> {
    private final Date date;

    public TaskDateOnDatePredicate(Date date) {
        this.date = date;
    }

    /**
     * Checks if date of this predicate is on the same date as the provided task's date.
     *
     * @param task Task with date to be checked with this predicate's date.
     * @return Boolean indicating whether the task's date is on the same date as this predicate's date.
     */
    @Override
    public boolean test(Task task) {
        return task.hasSameDate(date);
    }
}
