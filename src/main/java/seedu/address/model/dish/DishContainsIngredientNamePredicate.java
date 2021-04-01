package seedu.address.model.dish;

import java.util.function.Predicate;

public class DishContainsIngredientNamePredicate implements Predicate<Dish> {
    private final String ingredientName;

    public DishContainsIngredientNamePredicate(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public boolean test(Dish dish) {
        return dish.containsIngredientKeyword(ingredientName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DishContainsIngredientNamePredicate // instanceof handles nulls
                && ingredientName.equals(((DishContainsIngredientNamePredicate) other).ingredientName)); // state check
    }
}
