package seedu.budgetbaby.model.month;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import seedu.budgetbaby.commons.util.StringUtil;

/**
 * Tests that a {@code Financial Record}'s {@code Categories} matches any of the keywords given.
 */
public class CategoryPredicate implements Predicate<Month> {
    private final List<String> keywords;

    public CategoryPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Month month) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(month.getFinancialRecordList().get(0).getDescription().description, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.budgetbaby.model.month.CategoryPredicate // instanceof handles nulls
                && keywords.equals(((seedu.budgetbaby.model.month.CategoryPredicate) other).keywords)); // state check
    }

}
