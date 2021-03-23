package seedu.taskify.model.task.predicates;

import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.Task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Date} matches the specified date.
 */
public class TaskHasSameDatePredicate implements Predicate<Task> {
    private final Date inputDate;

    public TaskHasSameDatePredicate(Date date) {
        inputDate = date;
    }

    @Override
    public boolean test(Task task) {
        return this.inputDate.equals(task.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskHasSameDatePredicate // instanceof handles nulls
                && inputDate.equals(((TaskHasSameDatePredicate) other).inputDate)); // state check
    }
}
