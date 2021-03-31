package seedu.address.model.cheese;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Cheese}'s {@code Type} matches any of the keywords given.
 */
public class CheeseContainsKeywordsPredicate implements Predicate<Cheese> {
    private final List<String> keywords;

    public CheeseContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Cheese cheese) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(cheese.getCheeseType().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.cheese.CheeseContainsKeywordsPredicate
                && keywords.equals(((seedu.address.model.cheese.CheeseContainsKeywordsPredicate) other).keywords));
    }

}

