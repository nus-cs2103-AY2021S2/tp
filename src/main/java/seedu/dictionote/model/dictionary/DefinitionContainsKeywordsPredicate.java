package seedu.dictionote.model.dictionary;

import java.util.List;
import java.util.function.Predicate;

import seedu.dictionote.commons.util.StringUtil;

/**
 * Tests that a {@code Definition}'s {@code definition} matches any of the keywords given.
 */
public class DefinitionContainsKeywordsPredicate implements Predicate<Definition> {
    private final List<String> keywords;

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

