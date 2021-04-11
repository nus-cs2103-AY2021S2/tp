package seedu.address.model.entry;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Entry}'s {@code Tags} matches any of the keywords given.
 */
public class EntryTagsContainKeywordsPredicate implements Predicate<Entry> {
    private final List<String> keywords;

    public EntryTagsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        return keywords.stream()
                .allMatch(keyword -> entry.getTags().stream()
                        .anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryTagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EntryTagsContainKeywordsPredicate) other).keywords)); // state check
    }
}
