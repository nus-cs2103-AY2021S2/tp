package seedu.storemando.model.tag.predicate;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.storemando.commons.util.StringUtil;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.tag.Tag;

public class TagContainsKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        Set<Tag> tagSet = item.getTags();
        StringBuilder fullTags = new StringBuilder();
        for (Tag tag : tagSet) {
            String tagName = tag.tagName;
            fullTags.append(" ").append(tagName);
        }
        String finalFullTags = fullTags.toString();
        return keywords.stream()
            .allMatch(keyword -> StringUtil.containsWordIgnoreCase(finalFullTags, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
