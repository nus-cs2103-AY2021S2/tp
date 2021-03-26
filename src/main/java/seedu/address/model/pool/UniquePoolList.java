package seedu.address.model.pool;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.pool.exceptions.DuplicatePoolException;
import seedu.address.model.pool.exceptions.PoolNotFoundException;

/**
 * A list of pools that enforces uniqueness between its elements and does not allow nulls.
 * A pool is considered unique by comparing using {@code Pool#isSamePool(Pool)}. As such, adding and
 * updating of pool uses Pool#isSamePool(Pool) for equality so as to ensure that the pool being
 * added or updated is unique in terms of identity in the UniquePoolList. However, the removal of a pool uses
 * Pool#equals(Object) so as to ensure that the pool with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Pool#isSamePool(Pool)
 */
public class UniquePoolList implements Iterable<Pool> {

    private final ObservableList<Pool> internalList = FXCollections.observableArrayList();
    private final ObservableList<Pool> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent pool as the given argument.
     */
    public boolean contains(Pool toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePool);
    }

    /**
     * Adds a pool to the list.
     * The pool must not already exist in the list.
     */
    public void add(Pool toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePoolException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the pool {@code target} in the list with {@code editedPool}.
     * {@code target} must exist in the list.
     * The pool identity of {@code editedPool} must not be the same as another existing pool in the list.
     */
    public void setPool(Pool target, Pool editedPool) {
        requireAllNonNull(target, editedPool);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PoolNotFoundException();
        }

        if (!target.isSamePool(editedPool) && contains(editedPool)) {
            throw new DuplicatePoolException();
        }

        internalList.set(index, editedPool);
    }

    /**
     * Removes the equivalent pool from the list.
     * The pool must exist in the list.
     */
    public void remove(Pool toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PoolNotFoundException();
        }
    }

    public void setPools(UniquePoolList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code pools}.
     * {@code pools} must not contain duplicate pools.
     */
    public void setPools(List<Pool> pool) {
        requireAllNonNull(pool);
        if (!poolsAreUnique(pool)) {
            throw new DuplicatePoolException();
        }

        internalList.setAll(pool);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Pool> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Pool> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePoolList // instanceof handles nulls
                        && internalList.equals(((UniquePoolList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code pool} contains only unique pool.
     */
    private boolean poolsAreUnique(List<Pool> pool) {
        for (int i = 0; i < pool.size() - 1; i++) {
            for (int j = i + 1; j < pool.size(); j++) {
                if (pool.get(i).isSamePool(pool.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
