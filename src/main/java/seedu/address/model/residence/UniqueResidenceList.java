package seedu.address.model.residence;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.residence.exceptions.DuplicateResidenceException;
import seedu.address.model.residence.exceptions.ResidenceNotFoundException;


/**
 * A list of residences that enforces uniqueness between its elements and does not allow nulls.
 * A residence is considered unique by comparing using {@code Residence#isSameResidence(Residence)}.
 * As such, adding and updating of residences uses Residence#isSameResidence(Residence) for equality
 * so as to ensure that the residence being added or updated is unique in terms of identity in the UniqueResidenceList.
 * However, the removal of a Residence uses Residence#equals(Object) so as to ensure that the Residence
 * with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Residence#isSameResidence(Residence)
 */
public class UniqueResidenceList implements Iterable<Residence> {

    private final ObservableList<Residence> internalList = FXCollections.observableArrayList();
    private final ObservableList<Residence> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final Comparator<Residence> comparator = new ResidenceComparator();

    /**
     * Returns true if the list contains an equivalent residence as the given argument.
     */
    public boolean contains(Residence toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameResidence);
    }

    /**
     * Adds a Residence to the list.
     * The residence must not already exist in the list.
     */
    public void add(Residence toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateResidenceException();
        }
        internalList.add(toAdd);
        FXCollections.sort(internalList, comparator);
    }
    /**
     * Replaces the residence {@code target} in the list with {@code editedResidence}.
     * {@code target} must exist in the list.
     * The residence identity of {@code editedResidence} must not be the same as another existing residence in the list.
     */
    public void setResidence(Residence target, Residence editedResidence) {
        requireAllNonNull(target, editedResidence);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ResidenceNotFoundException();
        }

        if (!target.isSameResidence(editedResidence) && contains(editedResidence)) {
            throw new DuplicateResidenceException();
        }

        internalList.set(index, editedResidence);
        FXCollections.sort(internalList, comparator);
    }

    /**
     * Removes the equivalent residence from the list.
     * The residence must exist in the list.
     */
    public void remove(Residence toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ResidenceNotFoundException();
        }
    }

    public void setResidences(UniqueResidenceList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code residences}.
     * {@code residences} must not contain duplicate residences.
     */
    public void setResidences(List<Residence> residences) {
        requireAllNonNull(residences);
        if (!residencesAreUnique(residences)) {
            throw new DuplicateResidenceException();
        }

        internalList.setAll(residences);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Residence> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Residence> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueResidenceList // instanceof handles nulls
                && internalList.equals(((UniqueResidenceList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code residences} contains only unique residences.
     */
    private boolean residencesAreUnique(List<Residence> residences) {
        for (int i = 0; i < residences.size() - 1; i++) {
            for (int j = i + 1; j < residences.size(); j++) {
                if (residences.get(i).isSameResidence(residences.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
