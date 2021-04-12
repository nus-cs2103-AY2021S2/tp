package seedu.address.model.dish;

import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate that checks if this dish contains an ingredient with any keyword
 */
public class DishContainsIngredientNamePredicate implements Predicate<Dish> {
    private final List<String> keywords;

    /**
     * Initialize predicate with keywords
     * @param keywords keywords to look for
     */
    public DishContainsIngredientNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Dish dish) {
        return dish.containsIngredientKeywords(keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DishContainsIngredientNamePredicate // instanceof handles nulls
                && keywords.equals(((DishContainsIngredientNamePredicate) other).keywords)); // state check
    }
}
