package seedu.taskify.model.task.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.taskify.commons.util.StringUtil;
import seedu.taskify.model.task.Task;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                       .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                                   && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
