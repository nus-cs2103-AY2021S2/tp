package fooddiary.model.entry;

import java.util.List;
import java.util.function.Predicate;

import fooddiary.commons.util.StringUtil;
import fooddiary.model.tag.Tag;

/**
 * Tests that a {@code Entry}'s {@code Restaurant Name} matches all of the keywords given.
 */
public class NameContainsAllKeywordsPredicate implements Predicate<Entry> {
    private final List<String> keywords;

    public NameContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        assert(entry != null);
        if (keywords.isEmpty()) {
            return false;
        }
        //combine name, rating, price and address into a single string to test for keywords
        StringBuilder sb = new StringBuilder(entry.getName().fullName);
        sb.append(" ").append(entry.getRating().value).append("/5");
        sb.append(" ").append("$").append(entry.getPrice().value);
        sb.append(" ").append(entry.getAddress().value);
        for (Tag t : entry.getTags()) {
            sb.append(" ").append(t.tagCategory.name());
        }
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(sb.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsAllKeywordsPredicate) other).keywords)); // state check
    }

}
