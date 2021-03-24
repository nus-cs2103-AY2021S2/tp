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
        if (keywords.isEmpty()) {
            return false;
        }
        StringBuilder nameRatingAddressTag = new StringBuilder(entry.getName().fullName);
        nameRatingAddressTag.append(" ").append(entry.getRating().value).append("/5");
        nameRatingAddressTag.append(" ").append(entry.getAddress().value);
        for (Tag t : entry.getTags()) {
            nameRatingAddressTag.append(" ").append(t.tag);
        }
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(nameRatingAddressTag.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsAllKeywordsPredicate) other).keywords)); // state check
    }

}
