package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.owner.exceptions.DuplicateOwnerException;
import dog.pawbook.model.managedentity.owner.exceptions.OwnerNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of owners that enforces uniqueness between its elements and does not allow nulls.
 * A owner is considered unique by comparing using {@code Owner#isSameOwner(Owner)}. As such, adding and updating of
 * owners uses Owner#isSameOwner(Owner) for equality so as to ensure that the owner being added or updated is
 * unique in terms of identity in the UniqueOwnerList. However, the removal of a owner uses Owner#equals(Object) so
 * as to ensure that the owner with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Owner#isSameOwner(Owner)
 */

public class UniqueEntityList<T extends Entity> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent owner as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntity);
    }

    /**
     * Adds a owner to the list.
     * The owner must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateOwnerException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the owner {@code target} in the list with {@code editedOwner}.
     * {@code target} must exist in the list.
     * The owner identity of {@code editedOwner} must not be the same as another existing owner in the list.
     */
    public void setOwner(T target, T editedOwner) {
        requireAllNonNull(target, editedOwner);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new OwnerNotFoundException();
        }

        if (!target.isSameEntity(editedOwner) && contains(editedOwner)) {
            throw new DuplicateOwnerException();
        }

        internalList.set(index, editedOwner);
    }

    /**
     * Removes the equivalent owner from the list.
     * The owner must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new OwnerNotFoundException();
        }
    }

    public void setOwners(UniqueEntityList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code owners}.
     * {@code owners} must not contain duplicate owners.
     */
    public void setOwners(List<T> owners) {
        requireAllNonNull(owners);
        if (!ownersAreUnique(owners)) {
            throw new DuplicateOwnerException();
        }

        internalList.setAll(owners);
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
                || (other instanceof UniqueEntityList // instanceof handles nulls
                        && internalList.equals(((UniqueEntityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code owners} contains only unique owners.
     */
    private boolean ownersAreUnique(List<T> owners) {
        for (int i = 0; i < owners.size() - 1; i++) {
            for (int j = i + 1; j < owners.size(); j++) {
                if (owners.get(i).isSameEntity(owners.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
