package seedu.address.model.ingredient;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.UniqueItemList;

public class IngredientBook implements ReadOnlyIngredientBook {
    private final UniqueItemList<Ingredient> ingredients;
    {
        ingredients = new UniqueItemList<Ingredient>();
    }

    public IngredientBook() {}

    /**
     * Constructor to copy ingredient book
     * @param toBeCopied
     */
    public IngredientBook(ReadOnlyIngredientBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Set ingredients from list
     * @param ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setItems(ingredients);
    }

    /**
     * Reset list data using new data
     * @param newData
     */
    public void resetData(ReadOnlyIngredientBook newData) {
        requireNonNull(newData);

        setIngredients(newData.getIngredientList());
    }

    /**
     * Check if ingredient exists
     * @param ingredient
     * @return result
     */
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);

        return ingredients.contains(ingredient);
    }

    /**
     * Add new ingredient
     * @param o
     */
    public void addIngredient(Ingredient o) {
        ingredients.add(o);
    }

    /**
     * Set ingredient details
     * @param target
     * @param editedIngredient
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);
        ingredients.setItem(target, editedIngredient);
    }

    /**
     * Remove ingredient
     * @param key
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
    public ObservableList<Ingredient> getIngredientList() {
        return ingredients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientBook // instanceof handles nulls
                && ingredients.equals(((IngredientBook) other).ingredients));
    }

}
