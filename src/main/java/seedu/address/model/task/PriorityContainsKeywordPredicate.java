package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class PriorityContainsKeywordPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public PriorityContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getPriority().getPriority(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((PriorityContainsKeywordPredicate) other).keywords)); // state check
    }
}
