package seedu.address.model.event;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

public class DescriptionContainsKeywordsPredicate implements Predicate<GeneralEvent> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(GeneralEvent event) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(event.getDescription().description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DescriptionContainsKeywordsPredicate)) {
            return false;
        }
        if (other == this) {
            return true;
        }

        DescriptionContainsKeywordsPredicate otherPred =
                (DescriptionContainsKeywordsPredicate) other;
        return keywords.equals(otherPred.keywords);
    }
}
