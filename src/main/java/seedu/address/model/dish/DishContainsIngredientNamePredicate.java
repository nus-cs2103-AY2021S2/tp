package seedu.address.model.dish;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class DishContainsIngredientNamePredicate implements Predicate<Dish> {
    private final String ingredientName;

    public DishContainsIngredientNamePredicate(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public boolean test(Dish dish) {
        return dish.containsIngredientNameIgnoreCase(ingredientName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DishContainsIngredientNamePredicate // instanceof handles nulls
                && ingredientName.equals(((DishContainsIngredientNamePredicate) other).ingredientName)); // state check
    }
}
