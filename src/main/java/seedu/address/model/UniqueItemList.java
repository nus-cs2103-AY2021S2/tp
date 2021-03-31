package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.DuplicateItemException;
import seedu.address.model.exceptions.ItemNotFoundException;

public class UniqueItemList<T extends Item> implements Iterable<T> {
    private ObservableList<T> internalList = FXCollections.observableArrayList();
    private ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Check if list contains item
     * @param toCheck
     * @return result
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Add item to list
     * @param toAdd
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Set item
     * @param target
     * @param editedItem
     */
    public void setItem(T target, T editedItem) {
        requireAllNonNull(target, editedItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItemNotFoundException();
        }

        if (!target.isSame(editedItem) && contains(editedItem)) {
            throw new DuplicateItemException();
        }

        internalList.set(index, editedItem);
    }

    /**
     * Remove item
     * @param toRemove
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Set items based on replacement UniqueItemList
     * @param replacement
     */
    public void setItems(UniqueItemList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Set items based on list
     * @param list
     */
    public void setItems(List<T> list) {
        requireAllNonNull(list);
        if (!itemsAreUnique(list)) {
            throw new DuplicateItemException();
        }

        internalList.setAll(list);
    }

    /**
     * Get items in the list
     * @return
     */
    public ObservableList<T> getItems() {
        return internalList;
    }

    /**
     * Sorts items based on comparator
     * @param comparator
     */
    public void sort(Comparator<T> comparator) {
        internalList.sort(comparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueItemList) // instanceof handles nulls
                && internalList.equals(((UniqueItemList) other).internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean itemsAreUnique(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).isSame(list.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
