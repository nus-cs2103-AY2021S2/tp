package seedu.address.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Deadline} matches today's date.
 */
public class TaskDeadlineIsTodayPredicate implements Predicate<Task> {
    private final LocalDate todayDate = LocalDate.now();

    public TaskDeadlineIsTodayPredicate() { }

    @Override
    public boolean test(Task task) {
        return todayDate.isEqual(task.getDeadline().getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadlineIsTodayPredicate // instanceof handles nulls
                && todayDate.equals(((TaskDeadlineIsTodayPredicate) other).todayDate)); // state check
    }
}
