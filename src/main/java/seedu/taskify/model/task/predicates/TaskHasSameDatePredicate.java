package seedu.taskify.model.task.predicates;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.taskify.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code Date} matches the specified date.
 */
public class TaskHasSameDatePredicate implements Predicate<Task> {
    private final LocalDate inputDate;

    public TaskHasSameDatePredicate(LocalDate date) {
        inputDate = date;
    }

    @Override
    public boolean test(Task task) {
        return task.getDate().getLocalDateTime().toLocalDate().isEqual(this.inputDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskHasSameDatePredicate // instanceof handles nulls
                && inputDate.equals(((TaskHasSameDatePredicate) other).inputDate)); // state check
    }
}
