package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.UniqueItemList;

public class IngredientBook implements ReadOnlyBook<Ingredient> {
    private final UniqueItemList<Ingredient> ingredients;
    {
        ingredients = new UniqueItemList<Ingredient>();
    }

    public IngredientBook() {}

    /**
     * Constructor to copy ingredient book
     * @param toBeCopied ingredient book to be copied
     */
    public IngredientBook(ReadOnlyBook<Ingredient> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient: ingredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null;
    }

    /**
     * Set ingredients from list
     * @param ingredients list of ingredients used to replace
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setItems(ingredients);
    }

    /**
     * Reset list data using new data
     * @param newData new book data to be used
     */
    public void resetData(ReadOnlyBook<Ingredient> newData) {
        requireNonNull(newData);

        setIngredients(newData.getItemList());
    }

    /**
     * Check if ingredient exists
     * @param ingredient ingredient to check for
     * @return result
     */
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);

        return ingredients.contains(ingredient);
    }

    /**
     * Check if there are sufficient ingredients
     * @param ingredient ingredient to look for
     * @param quantity quantity needed
     * @return if there are sufficient ingredients
     */
    public boolean hasSufficientIngredients(Ingredient ingredient, int quantity) {
        for (Ingredient i : ingredients) {
            if (i.isSame(ingredient)) {
                return i.getQuantity() >= quantity;
            }
        }
        return false;
    }

    /**
     * Add new ingredient
     * @param ingredient ingreident to add
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Set ingredient details
     * @param target target ingredient to be edited
     * @param editedIngredient edited version of ingredient
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);
        ingredients.setItem(target, editedIngredient);
    }

    /**
     * Remove ingredient
     * @param key ingredient to remove
     */
    public void removeIngredient(Ingredient key) {
        ingredients.remove(key);
    }

    @Override
    public String toString() {
        return ingredients.asUnmodifiableObservableList().size() + " Ingredients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Ingredient> getItemList() {
        return ingredients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientBook // instanceof handles nulls
                && ingredients.equals(((IngredientBook) other).ingredients));
    }

}
