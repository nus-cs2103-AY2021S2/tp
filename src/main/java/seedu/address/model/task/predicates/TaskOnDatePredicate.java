package seedu.address.model.task.predicates;

import java.util.function.Predicate;

import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Date;

/**
 * Predicate testing whether the task being tested falls on the date provided. Uses
 * {@code TaskScheduledOnDatePredicate} and {@code TaskDateOnDatePredicate} to checks the date with the task's
 * date and recurring schedule.
 */
public class TaskOnDatePredicate implements Predicate<Task> {
    private final TaskDateOnDatePredicate taskDateOnDatePredicate;
    private final TaskScheduledOnDatePredicate taskScheduledOnDatePredicate;

    /**
     * Constructs this predicate that tests if a given task has its date or schedule on the provided date date.
     * Uses date as the parameter to construct this predicate so as to ensure that the date has been parsed
     * correctly.
     *
     * @param date Date with date to test tasks' date and schedule with.
     */
    public TaskOnDatePredicate(Date date) {
        this.taskDateOnDatePredicate = new TaskDateOnDatePredicate(date);
        this.taskScheduledOnDatePredicate = new TaskScheduledOnDatePredicate(date.toString());
    }

    @Override
    public boolean test(Task task) {
        boolean isTaskOnDate = taskDateOnDatePredicate.test(task);
        boolean isTaskScheduledOnDate = taskScheduledOnDatePredicate.test(task);
        return isTaskOnDate || isTaskScheduledOnDate;
    }
}
