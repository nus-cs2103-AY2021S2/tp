package seedu.address.model.task.predicates;

import java.util.function.Predicate;

import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

/**
 * Predicate testing whether the task's recurring schedule contains on the date provided.
 */
public class TaskScheduledOnDatePredicate implements Predicate<Task> {
    private final String dateString;

    public TaskScheduledOnDatePredicate(String dateString) {
        this.dateString = dateString;
    }

    /**
     * Checks if date of this predicate in the provided task's recurring schedule.
     *
     * @param task Task with recurring schedule to be checked with this predicate's date.
     * @return Boolean indicating whether the task's schedule contains this predicate's date.
     */
    @Override
    public boolean test(Task task) {
        return new AttributeManager(task).isOnRecurringScheduleDate(dateString);
    }
}
