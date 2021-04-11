package seedu.address.model.dish;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Predicate that checks if this dish name contains keywords
 */
public class DishNameContainsWordsPredicate implements Predicate<Dish> {
    private final List<String> keywords;

    /**
     * Initialize predicate with list of keywords
     * @param keywords keywords to search for
     */
    public DishNameContainsWordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Dish dish) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(dish.getName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DishNameContainsWordsPredicate // instanceof handles nulls
                && keywords.equals(((DishNameContainsWordsPredicate) other).keywords)); // state check
    }
}
