package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class TitleContainsKeywordPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TitleContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getTitle().taskTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsKeywordPredicate) other).keywords)); // state check
    }
}
