package seedu.address.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.grade.exceptions.DuplicateGradeException;
import seedu.address.model.grade.exceptions.GradeNotFoundException;
import seedu.address.model.subject.exceptions.SubjectNotFoundException;

public class GradeList implements Iterable<Grade> {
    private final ObservableList<Grade> internalList = FXCollections.observableArrayList();
    private final ObservableList<Grade> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent grade as the given argument.
     */
    public boolean contains(Grade toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a grade to the list.
     * The grade must not already exist in the list.
     */
    public void add(Grade toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGradeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the grade {@code target} in the list with {@code editedGrade}.
     * {@code target} must exist in the list.
     * The grade identity of {@code editedGrade} must not be the same as another existing grade in the list.
     */
    public void setGrade(Grade target, Grade editedGrade) {
        requireAllNonNull(target, editedGrade);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new GradeNotFoundException();
        }

        if (!target.equals(editedGrade) && contains(editedGrade)) {
            throw new DuplicateGradeException();
        }

        internalList.set(index, editedGrade);
    }

    /**
     * Removes the equivalent grade from the list.
     * The grade must exist in the list.
     */
    public void remove(Grade toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SubjectNotFoundException();
        }
    }

    /**
     * Removes the equivalent grade from the list (must be present).
     * @param index Index of grade to remove (0-based)
     */
    public void remove(int index) {
        this.internalList.remove(index);
    }

    public void setGrades(seedu.address.model.grade.GradeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code grades}.
     * {@code grades} must not contain duplicate grades.
     */
    public void setGrades(List<Grade> grades) {
        requireAllNonNull(grades);
        if (!gradesAreUnique(grades)) {
            throw new DuplicateGradeException();
        }

        internalList.setAll(grades);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Grade> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Grade> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.grade.Grade // instanceof handles nulls
                && internalList.equals(((seedu.address.model.grade.GradeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code subjects} contains only unique grades.
     */
    private boolean gradesAreUnique(List<Grade> grades) {
        for (int i = 0; i < grades.size() - 1; i++) {
            for (int j = i + 1; j < grades.size(); j++) {
                if (grades.get(i).equals(grades.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
