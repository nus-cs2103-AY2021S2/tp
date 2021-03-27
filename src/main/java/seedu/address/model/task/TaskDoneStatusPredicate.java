package seedu.address.model.task;

import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Status} matches the keyword given.
 */
public class TaskDoneStatusPredicate implements Predicate<Task> {
    private final String keyword;

    /**
     * TaskDoneStatusPredicate constructor
     */
    public TaskDoneStatusPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Task task) {
        String taskStatus = task.getStatus().value;
        return taskStatus.equals(this.keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDoneStatusPredicate // instanceof handles nulls
                && keyword.equals(((TaskDoneStatusPredicate) other).keyword)); // state check
    }

}
