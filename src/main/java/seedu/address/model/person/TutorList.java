package seedu.address.model.person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.Person;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


public class TutorList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutor as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds tutor to the list.
     * The tutor must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    public void setTutor(Person target, Person editedTutor) {
        requireAllNonNull(target, editedTutor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedTutor) && contains(editedTutor)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedTutor);
    }

    /**
     * Removes the equivalent tutor from the list.
     * The tutor must exist in the list.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
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
    public void setTutors(List<Person> tutors) {
        requireAllNonNull(tutors);
        if (!tutorsAreUnique(tutors)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(tutors);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
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
    private boolean tutorsAreUnique(List<Person> tutors) {
        for (int i = 0; i < tutors.size() - 1; i++) {
            for (int j = i + 1; j < tutors.size(); j++) {
                if (tutors.get(i).isSamePerson(tutors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
