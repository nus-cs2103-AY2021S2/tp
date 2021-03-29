package seedu.address.model.task.predicates;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

/**
 * Predicate testing whether the task being tested falls on the date provided. Uses
 * {@code TaskScheduledOnDatePredicate} and {@code TaskDeadlineOnDatePredicate} to checks the date with the task's
 * deadline and recurring schedule.
 */
public class TaskOnDatePredicate implements Predicate<Task> {
    private final String dateString;
    private final TaskDeadlineOnDatePredicate taskDeadlineOnDatePredicate;
    private final TaskScheduledOnDatePredicate taskScheduledOnDatePredicate;

    /**
     * Constructs this predicate that tests if a given task has its deadline or schedule on the provided deadline date.
     * Uses deadline as the parameter to construct this predicate so as to ensure that the date has been parsed
     * correctly.
     *
     * @param deadline Deadline with date to test tasks' deadline and schedule with.
     */
    public TaskOnDatePredicate(Deadline deadline) {
        this.dateString = deadline.toString();
        this.taskDeadlineOnDatePredicate = new TaskDeadlineOnDatePredicate(deadline);
        this.taskScheduledOnDatePredicate = new TaskScheduledOnDatePredicate(dateString);
    }

    @Override
    public boolean test(Task task) {
        boolean isTaskOnDeadlineDate = taskDeadlineOnDatePredicate.test(task);
        boolean isTaskScheduledOnDate = taskScheduledOnDatePredicate.test(task);
        return isTaskOnDeadlineDate || isTaskScheduledOnDate;
    }
}
