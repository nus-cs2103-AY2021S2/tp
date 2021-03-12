package seedu.address.model.dish;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.dish.exceptions.DishNotFoundException;
import seedu.address.model.dish.exceptions.DuplicateDishException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueDishList implements Iterable<Dish> {

    private final ObservableList<Dish> internalList = FXCollections.observableArrayList();
    private final ObservableList<Dish> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Dish toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDish);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Dish toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDishException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setDish(Dish target, Dish editedDish) {
        requireAllNonNull(target, editedDish);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DishNotFoundException();
        }

        if (!target.isSameDish(editedDish) && contains(editedDish)) {
            throw new DishNotFoundException();
        }

        internalList.set(index, editedDish);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Dish toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DishNotFoundException();
        }
    }

    public void setDishes(UniqueDishList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code dishes}.
     * {@code dishes} must not contain duplicate persons.
     */
    public void setDishes(List<Dish> dishes) {
        requireAllNonNull(dishes);
        if (!dishesAreUnique(dishes)) {
            throw new DuplicateDishException();
        }

        internalList.setAll(dishes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Dish> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Dish> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDishList // instanceof handles nulls
                && internalList.equals(((UniqueDishList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean dishesAreUnique(List<Dish> dishes) {
        for (int i = 0; i < dishes.size() - 1; i++) {
            for (int j = i + 1; j < dishes.size(); j++) {
                if (dishes.get(i).isSameDish(dishes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
