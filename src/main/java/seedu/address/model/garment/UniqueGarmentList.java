package seedu.address.model.garment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.garment.exceptions.DuplicateGarmentException;
import seedu.address.model.garment.exceptions.GarmentNotFoundException;

/**
 * A list of garments that enforces uniqueness between its elements and does not allow nulls.
 * A garment is considered unique by comparing using {@code Garment#isSameGarment(Garment)}.
 * As such, adding and updating of garments uses Garment#isSameGarment(Garment)
 * for equality so as to ensure that the garment being added or updated is
 * unique in terms of identity in the UniqueGarmentList.
 * However, the removal of a garment uses Garment#equals(Object) so
 * as to ensure that the garment with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Garment#isSameGarment(Garment)
 */
public class UniqueGarmentList implements Iterable<Garment> {

    private final ObservableList<Garment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Garment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void sort() {
        internalList.sort(new GarmentComparator());
    }

    /**
     * Returns true if the list contains an equivalent garment as the given argument.
     */
    public boolean contains(Garment toCheck) {
        requireNonNull(toCheck);
        this.sort();
        return internalList.stream().anyMatch(toCheck::isSameGarment);
    }

    /**
     * Adds a garment to the list.
     * The garment must not already exist in the list.
     */
    public void add(Garment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGarmentException();
        }
        internalList.add(toAdd);
        this.sort();
    }

    /**
     * Replaces the garment {@code target} in the list with {@code editedGarment}.
     * {@code target} must exist in the list.
     * The garment identity of {@code editedGarment} must not be the same as another existing garment in the list.
     */
    public void setGarment(Garment target, Garment editedGarment) {
        requireAllNonNull(target, editedGarment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new GarmentNotFoundException();
        }

        if (!target.isSameGarment(editedGarment) && contains(editedGarment)) {
            throw new DuplicateGarmentException();
        }

        internalList.set(index, editedGarment);
        this.sort();
    }

    /**
     * Removes the equivalent garment from the list.
     * The garment must exist in the list.
     */
    public void remove(Garment toRemove) {
        requireNonNull(toRemove);
        this.sort();
        if (!internalList.remove(toRemove)) {
            throw new GarmentNotFoundException();
        }
    }

    public void setGarments(UniqueGarmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        this.sort();
    }

    /**
     * Replaces the contents of this list with {@code garments}.
     * {@code garments} must not contain duplicate garments.
     */
    public void setGarments(List<Garment> garments) {
        requireAllNonNull(garments);
        if (!garmentsAreUnique(garments)) {
            throw new DuplicateGarmentException();
        }

        internalList.setAll(garments);
        this.sort();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Garment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Garment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueGarmentList // instanceof handles nulls
                        && internalList.equals(((UniqueGarmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code garments} contains only unique garments.
     */
    private boolean garmentsAreUnique(List<Garment> garments) {
        for (int i = 0; i < garments.size() - 1; i++) {
            for (int j = i + 1; j < garments.size(); j++) {
                if (garments.get(i).isSameGarment(garments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
