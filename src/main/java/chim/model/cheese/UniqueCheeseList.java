package chim.model.cheese;

import static chim.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import chim.model.AbstractDate;
import chim.model.cheese.exceptions.CheeseNotFoundException;
import chim.model.cheese.exceptions.DuplicateCheeseException;
import chim.model.order.Quantity;
import chim.model.order.exceptions.DuplicateOrderException;
import chim.model.order.exceptions.OrderNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of Cheese that enforces uniqueness between its elements and does not allow nulls.
 * A Cheese is considered unique by comparing using {@code Cheese#isSameCheese(Cheese)}. As such, adding and updating of
 * cheeses uses Cheese#isSameCheese(Cheese) for equality so as to ensure that the Cheese being added or updated is
 * unique in terms of identity in the UniqueCheeseList. However, the removal of a Cheese uses Cheese#equals(Cheese) so
 * as to ensure that the Cheese with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Cheese#isSameCheese(Cheese)
 */
public class UniqueCheeseList implements Iterable<Cheese> {
    private final ObservableList<Cheese> internalList = FXCollections.observableArrayList();
    private final ObservableList<Cheese> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent cheese as the given argument.
     */
    public boolean contains(Cheese toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCheese);
    }

    /**
     * Returns first {@code Cheese} instance with equivalent cheeseId as the given
     * argument or null if it does not exist.
     */
    public Cheese getCheeseWithId(CheeseId cheeseId) {
        requireNonNull(cheeseId);
        return internalList.stream().filter(cheese -> cheese.getCheeseId().equals(cheeseId))
            .findFirst().orElse(null);
    }

    /**
     * Adds a Cheese to the list.
     * The cheese must not already exist in the list.
     */
    public void add(Cheese toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCheeseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Cheese {@code target} in the list with {@code editedCheese}.
     * {@code target} must exist in the list.
     * The Cheese identity of {@code editedCheese} must not be the same as another existing Cheese in the list.
     */
    public void setCheese(Cheese target, Cheese editedCheese) {
        requireAllNonNull(target, editedCheese);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OrderNotFoundException();
        }

        if (!target.isSameCheese(editedCheese) && contains(editedCheese)) {
            throw new DuplicateOrderException();
        }

        internalList.set(index, editedCheese);
    }

    /**
     * Removes the equivalent cheese from the list.
     * The cheese must exist in the list.
     */
    public void delete(Cheese toDelete) {
        requireNonNull(toDelete);
        if (!internalList.remove(toDelete)) {
            throw new CheeseNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code cheeses}.
     * {@code cheeses} must not contain duplicate cheeses.
     */
    public void setCheeses(List<Cheese> cheeses) {
        requireAllNonNull(cheeses);
        if (!cheesesAreUnique(cheeses)) {
            throw new DuplicateCheeseException();
        }

        internalList.setAll(cheeses);
    }

    public Set<CheeseId> getUnassignedCheeses(CheeseType cheeseType, Quantity quantity) {
        requireAllNonNull(cheeseType, quantity);

        Set<CheeseId> cheeses = new HashSet<>();
        int c = quantity.getQuantity();
        AbstractDate abstractDateNow = ManufactureDate.now();
        for (int i = 0; i < internalList.size() && c > 0; i++) {
            Cheese cheese = internalList.get(i);
            if (!cheese.isCheeseAssigned() && cheese.isSameType(cheeseType)
                    && cheese.getExpiryDate().map(x -> x.isAfter(abstractDateNow)).orElse(true)) {
                cheeses.add(cheese.getCheeseId());
                c--;
            }
        }
        return cheeses;
    }

    /**
     * Updates cheeses to assigned based on the set of cheese Ids provided
     * @param cheesesAssigned , cheese Ids of cheeses assigned to an order
     */
    public void updateCheesesStatus(Set<CheeseId> cheesesAssigned) {
        requireAllNonNull(cheesesAssigned);

        for (int i = 0; i < internalList.size(); i++) {
            Cheese cheese = internalList.get(i);
            if (cheesesAssigned.contains(cheese.getCheeseId())) {
                internalList.set(i, cheese.assignToOrder());
            }
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Cheese> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Cheese> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCheeseList // instanceof handles nulls
                && internalList.equals(((UniqueCheeseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code cheeses} contains only unique cheeses.
     */
    private boolean cheesesAreUnique(List<Cheese> cheeses) {
        for (int i = 0; i < cheeses.size() - 1; i++) {
            for (int j = i + 1; j < cheeses.size(); j++) {
                if (cheeses.get(i).isSameCheese(cheeses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
