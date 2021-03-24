package seedu.taskify.model.task.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.taskify.commons.util.StringUtil;
import seedu.taskify.model.tag.Tag;
import seedu.taskify.model.task.Task;


/**
 * Tests that a {@code Task}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {

        Set<Tag> tags = task.getTags();
        String tagsString = tags.stream()
                                .map(tag -> tag.toString() + " ")
                                .map(tag -> tag.replaceAll("[\\[\\]]*", ""))
                                .reduce("", String::concat);

        return keywords.stream()
                       .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tagsString,
                               keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                                   && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
