package seedu.address.model.entry;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class EntryNameContainsKeywordsPredicate implements Predicate<Entry> {

    private final String keyword;

    public EntryNameContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Entry entry) {
        return StringUtil.containsWordIgnoreCase(entry.getEntryName().name, keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryNameContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((EntryNameContainsKeywordsPredicate) other).keyword)); // state check
    }
}
