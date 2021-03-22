package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> descriptionWords;

    /**
     * DescriptionContainsKeywordsPredicate constructor
     *
     * @param descriptionWords A list of words within the find by description query
     */
    public DescriptionContainsKeywordsPredicate(List<String> descriptionWords) {
        this.descriptionWords = descriptionWords;
    }

    @Override
    public boolean test(Task task) {
        String description = task.getDescription().value;
        return descriptionWords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DescriptionContainsKeywordsPredicate // instanceof handles nulls
                && descriptionWords.equals(((DescriptionContainsKeywordsPredicate) other)
                .descriptionWords)); // state check
    }
}
