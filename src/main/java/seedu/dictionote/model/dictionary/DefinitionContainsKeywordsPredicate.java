package seedu.dictionote.model.dictionary;

import java.util.List;
import java.util.function.Predicate;

import seedu.dictionote.commons.util.StringUtil;

//Todo
public class DefinitionContainsKeywordsPredicate implements Predicate<Definition> {
    private final List<String> keywords;

    //Todo
    public DefinitionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Definition definition) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(definition.getTerm(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DefinitionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DefinitionContainsKeywordsPredicate) other).keywords)); // state check
    }
}

