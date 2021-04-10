package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of patients that enforces uniqueness between its elements and does not allow nulls.
 * A patient is considered unique by comparing using {@code Patient#isSamePerson(Patient)}. As such, adding and updating
 * of persons uses Patient#isSamePerson(Patient) for equality so as to ensure that the patient being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a patient uses Patient#equals(Object) so
 * as to ensure that the patient with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Patient#isSamePerson(Patient)
 */
public class UniquePersonList implements Iterable<Patient> {

    private final ObservableList<Patient> internalList = FXCollections.observableArrayList();
    private final ObservableList<Patient> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent patient as the given argument.
     */
    public boolean contains(Patient toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a patient to the list.
     * The patient must not already exist in the list.
     */
    public void add(Patient toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
        Collections.sort(internalList);
    }

    /**
     * Replaces the patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the list.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the list.
     */
    public void setPerson(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPatient) && contains(editedPatient)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPatient);
    }

    /**
     * Removes the equivalent patient from the list.
     * The person must exist in the list.
     */
    public void remove(Patient toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Archives the equivalent patient in the list.
     * The person must exist in the list.
     */
    public void archive(Patient toArchive) {
        requireNonNull(toArchive);
        remove(toArchive);
        toArchive.setArchived(true);
        add(toArchive);
    }

    /**
     * Unarchives the equivalent patient in the list.
     * The person must exist in the list.
     */
    public void unarchive(Patient toUnarchive) {
        requireNonNull(toUnarchive);
        remove(toUnarchive);
        toUnarchive.setArchived(false);
        add(toUnarchive);
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPersons(List<Patient> patients) {
        requireAllNonNull(patients);
        if (!personsAreUnique(patients)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(patients);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Patient> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Patient> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code patients} contains only unique patients.
     */
    private boolean personsAreUnique(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).isSamePerson(patients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
