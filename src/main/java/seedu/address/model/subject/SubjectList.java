package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.subject.exceptions.DuplicateSubjectException;
import seedu.address.model.subject.exceptions.SubjectNotFoundException;

public class SubjectList implements Iterable<TutorSubject> {

    private final ObservableList<TutorSubject> internalList = FXCollections.observableArrayList();
    private final ObservableList<TutorSubject> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent subject as the given argument.
     */
    public boolean contains(TutorSubject toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a subject to the list.
     * The subject must not already exist in the list.
     */
    public void add(TutorSubject toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateSubjectException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the subject {@code target} in the list with {@code editedSubject}.
     * {@code target} must exist in the list.
     * The subject identity of {@code editedSubject} must not be the same as another existing subject in the list.
     */
    public void setSubject(TutorSubject target, TutorSubject editedSubject) {
        requireAllNonNull(target, editedSubject);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new SubjectNotFoundException();
        }

        if (!target.equals(editedSubject) && contains(editedSubject)) {
            throw new DuplicateSubjectException();
        }

        internalList.set(index, editedSubject);
    }

    /**
     * Removes the equivalent subject from the list.
     * The subject must exist in the list.
     */
    public void remove(TutorSubject toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SubjectNotFoundException();
        }
    }

    public void setSubjects(seedu.address.model.subject.SubjectList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code subjects}.
     * {@code subjects} must not contain duplicate subjects.
     */
    public void setSubjects(List<TutorSubject> subjects) {
        requireAllNonNull(subjects);
        if (!subjectsAreUnique(subjects)) {
            throw new DuplicateSubjectException();
        }

        internalList.setAll(subjects);
    }

    /**
     * Returns the number of subjects in this list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TutorSubject> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TutorSubject> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectList // instanceof handles nulls
                && internalList.equals(((SubjectList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code subjects} contains only unique subjects.
     */
    private boolean subjectsAreUnique(List<TutorSubject> subjects) {
        for (int i = 0; i < subjects.size() - 1; i++) {
            for (int j = i + 1; j < subjects.size(); j++) {
                if (subjects.get(i).equals(subjects.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code subjects} contains only unique subjects.
     */
    public static boolean isValidSubjectList(List<TutorSubject> subjects) {
        for (TutorSubject ts : subjects) {
            if (!TutorSubject.isValidTutorSubject(ts)) {
                return false;
            }
        }
        return true;
    }
}
