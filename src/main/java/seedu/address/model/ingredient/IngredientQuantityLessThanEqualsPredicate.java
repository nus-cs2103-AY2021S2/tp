package seedu.address.model.ingredient;

import java.util.function.Predicate;

/**
 * Predicate that checks if ingredient's quantity has less than or equals to this number.
 */
public class IngredientQuantityLessThanEqualsPredicate implements Predicate<Ingredient> {
    private final int quantity;

    /**
     * Initialize predicate with quantity
     * @param quantity less than quantity to check for
     */
    public IngredientQuantityLessThanEqualsPredicate(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean test(Ingredient ingredient) {
        return ingredient.getQuantity() <= quantity;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientQuantityLessThanEqualsPredicate // instanceof handles nulls
                && quantity == ((IngredientQuantityLessThanEqualsPredicate) other).quantity); // state check
    }
}
