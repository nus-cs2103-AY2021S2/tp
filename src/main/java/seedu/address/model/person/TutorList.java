package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


// To implement:
// Class: Tutor, DuplicateTagException, TagNotFoundException
// Method: isSameTutor
public class TutorList implements Iterable<Tutor> {

    private final ObservableList<Tutor> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tutor> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutor as the given argument.
     */
    public boolean contains(Tutor toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTutor);
    }

    /**
     * Adds tutor to the list.
     * The tutor must not already exist in the list.
     */
    public void add(Tutor toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorException();
        }
        internalList.add(toAdd);
    }

    public void setTutor(Tutor target, Tutor editedTutor) {
        requireAllNonNull(target, editedTutor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TutorNotFoundException();
        }

        if (!target.isSameTutor(editedTutor) && contains(editedTutor)) {
            throw new DuplicateTutorException();
        }

        internalList.set(index, editedTutor);
    }

    /**
     * Removes the equivalent tutor from the list.
     * The tutor must exist in the list.
     */
    public void remove(Tutor toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorNotFoundException();
        }
    }

    public void setTutors(seedu.address.model.person.TutorList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tutors}.
     * {@code tutors} must not contain duplicate tutors.
     */
    public void setTutors(List<Tutor> tutors) {
        requireAllNonNull(tutors);
        if (!tutorsAreUnique(tutors)) {
            throw new DuplicateTutorException();
        }

        internalList.setAll(tutors);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tutor> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tutor> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorList // instanceof handles nulls
                && internalList.equals(((TutorList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tutors} contains only unique tutors.
     */
    private boolean tutorsAreUnique(List<Tutor> tutors) {
        for (int i = 0; i < tutors.size() - 1; i++) {
            for (int j = i + 1; j < tutors.size(); j++) {
                if (tutors.get(i).isSameTutor(tutors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
