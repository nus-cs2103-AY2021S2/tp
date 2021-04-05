package seedu.heymatez.model.task;

import java.util.function.Predicate;

import seedu.heymatez.model.assignee.Assignee;

/**
 * Tests that a {@code Task}'s {@code Assignee} matches the keyword given.
 */
public class TaskContainsAssigneePredicate implements Predicate<Task> {
    private final String keyword;

    public TaskContainsAssigneePredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Task task) {
        return task.hasAssignee(new Assignee(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsAssigneePredicate // instanceof handles nulls
                && keyword.equals(((TaskContainsAssigneePredicate) other).keyword)); // state check
    }
}
