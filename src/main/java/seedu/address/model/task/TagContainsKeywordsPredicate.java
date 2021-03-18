package seedu.address.model.task;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Task}'s tags matches all of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Task> {
    private final Set<String> tagWords;

    /**
     * TagContainsKeywordsPredicate constructor
     *
     * @param tagWords A set of words within the find by tag query
     */
    public TagContainsKeywordsPredicate(Set<String> tagWords) {
        this.tagWords = tagWords;
    }

    @Override
    public boolean test(Task task) {
        Set<Tag> tags = task.getTags();
        Set<String> tagNames = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        for (Tag currTag : tags) {
            tagNames.add(currTag.tagName);
        }
        return tagNames.containsAll(tagWords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && tagWords.equals(((TagContainsKeywordsPredicate) other).tagWords)); // state check
    }
}
