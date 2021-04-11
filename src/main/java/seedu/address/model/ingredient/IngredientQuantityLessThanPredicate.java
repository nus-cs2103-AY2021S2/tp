package seedu.address.model.ingredient;

import java.util.function.Predicate;

/**
 * Predicate that checks if ingredient's quantity has less than this number.
 */
public class IngredientQuantityLessThanPredicate implements Predicate<Ingredient> {
    private final int quantity;

    /**
     * Initialize predicate with quantity
     * @param quantity less than quantity to check for
     */
    public IngredientQuantityLessThanPredicate(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean test(Ingredient ingredient) {
        return ingredient.getQuantity() < quantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientQuantityLessThanPredicate // instanceof handles nulls
                && quantity == ((IngredientQuantityLessThanPredicate) other).quantity); // state check
    }
}
