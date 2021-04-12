package seedu.address.model.entry;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Entry}'s {@code EntryName} matches all of the keywords given.
 */
public class EntryNameContainsKeywordsPredicate implements Predicate<Entry> {

    private final List<String> keywords;

    public EntryNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(entry.getEntryName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EntryNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
