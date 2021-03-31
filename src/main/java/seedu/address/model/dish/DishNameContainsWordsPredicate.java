package seedu.address.model.dish;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class DishNameContainsWordsPredicate implements Predicate<Dish> {
    private final List<String> keywords;

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
