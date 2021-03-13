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

    public IngredientBook(ReadOnlyIngredientBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setItems(ingredients);
    }

    public void resetData(ReadOnlyIngredientBook newData) {
        requireNonNull(newData);

        setIngredients(newData.getIngredientList());
    }

    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);

        return ingredients.contains(ingredient);
    }

    public void addIngredient(Ingredient o) {
        ingredients.add(o);
    }

    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);
        ingredients.setItem(target, editedIngredient);
    }

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
