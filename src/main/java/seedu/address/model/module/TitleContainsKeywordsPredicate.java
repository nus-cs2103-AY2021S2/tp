package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
public class TitleContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(module.getTitle().modTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TitleContainsKeywordsPredicate)
                && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords);
    }
}
