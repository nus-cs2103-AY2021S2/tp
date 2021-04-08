package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;

public class UniquePropertyList implements Iterable<Property> {

    private final ObservableList<Property> internalList = FXCollections.observableArrayList();
    private final ObservableList<Property> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Property as the given argument.
     */
    public boolean contains(Property toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameProperty);
    }

    /**
     * Returns true if the list is empty.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Adds a Property to the list.
     * The Property must not already exist in the list.
     */
    public void add(Property toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePropertyException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Property {@code target} in the list with {@code editedProperty}.
     * {@code target} must exist in the list.
     * The Property identity of {@code editedProperty} must not be the same as another existing Property in the list.
     */
    public void setProperty(Property target, Property editedProperty) {
        requireAllNonNull(target, editedProperty);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PropertyNotFoundException();
        }

        if (!target.isSameProperty(editedProperty) && contains(editedProperty)) {
            throw new DuplicatePropertyException();
        }

        internalList.set(index, editedProperty);
    }

    /**
     * Removes the equivalent Property from the list.
     * The Property must exist in the list.
     */
    public void remove(Property toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PropertyNotFoundException();
        }
    }

    public void setProperties(UniquePropertyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code properties}.
     * {@code properties} must not contain duplicate Properties.
     */
    public void setProperties(List<Property> properties) {
        requireAllNonNull(properties);
        if (!propertiesAreUnique(properties)) {
            throw new DuplicatePropertyException();
        }

        internalList.setAll(properties);
    }

    /**
     * Sorts property list using the specified comparator {@code comparator}.
     */
    public void sortProperties(Comparator<Property> comparator) {
        internalList.sort(comparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Property> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Property> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePropertyList // instanceof handles nulls
                && internalList.equals(((UniquePropertyList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Properties} contains only unique Properties.
     */
    private boolean propertiesAreUnique(List<Property> properties) {
        for (int i = 0; i < properties.size() - 1; i++) {
            for (int j = i + 1; j < properties.size(); j++) {
                if (properties.get(i).isSameProperty(properties.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
