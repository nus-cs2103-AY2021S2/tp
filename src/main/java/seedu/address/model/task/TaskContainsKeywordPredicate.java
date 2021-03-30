package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Title} or {@code Description} matches any of the keywords given.
 */
public class TaskContainsKeywordPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        boolean title = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTitle().taskTitle, keyword));

        boolean description = keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription().desc, keyword));

        return title || description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((TaskContainsKeywordPredicate) other).keywords)); // state check
    }
}
