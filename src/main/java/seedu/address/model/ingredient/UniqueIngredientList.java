package seedu.address.model.Ingredient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;
import seedu.address.model.ingredient.exceptions.DuplicateIngredientException;
import seedu.address.model.ingredient.Ingredient;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueIngredientList implements Iterable<Ingredient> {

    private final ObservableList<Ingredient> internalList = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Ingredient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameIngredient);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Ingredient toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateIngredientException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireAllNonNull(target, editedIngredient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new IngredientNotFoundException();
        }

        if (!target.isSameIngredient(editedIngredient) && contains(editedIngredient)) {
            throw new IngredientNotFoundException();
        }

        internalList.set(index, editedIngredient);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Ingredient toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new IngredientNotFoundException();
        }
    }

    public void setIngredients(UniqueIngredientList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Ingredientes}.
     * {@code Ingredientes} must not contain duplicate persons.
     */
    public void setIngredients(List<Ingredient> Ingredients) {
        requireAllNonNull(Ingredients);
        if (!ingredientsAreUnique(Ingredients)) {
            throw new DuplicateIngredientException();
        }

        internalList.setAll(Ingredients);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Ingredient> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueIngredientList // instanceof handles nulls
                && internalList.equals(((UniqueIngredientList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean ingredientsAreUnique(List<Ingredient> Ingredients) {
        for (int i = 0; i < Ingredients.size() - 1; i++) {
            for (int j = i + 1; j < Ingredients.size(); j++) {
                if (Ingredients.get(i).isSameIngredient(Ingredients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
