package seedu.dictionote.model.dictionary;

import java.util.List;
import java.util.function.Predicate;

import seedu.dictionote.commons.util.StringUtil;

/**
 * Tests that a {@code Content}'s {@code content} matches any of the keywords given.
 */
public class ContentContainsKeywordsPredicate implements Predicate<Content> {
    private final List<String> keywords;

    public ContentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Content content) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(content.getMainContent(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContentContainsKeywordsPredicate) other).keywords)); // state check
    }
}
