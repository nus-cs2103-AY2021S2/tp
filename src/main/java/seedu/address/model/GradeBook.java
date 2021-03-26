package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeList;


/**
 * Wraps all data at the grade-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class GradeBook implements ReadOnlyGradeBook {

    private final GradeList grades;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        grades = new GradeList();
    }

    public GradeBook() {
    }

    /**
     * Creates an GradeBook using the Grades in the {@code toBeCopied}
     */
    public GradeBook(ReadOnlyGradeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the grade list with {@code grades}.
     * {@code grades} must not contain duplicate grades.
     */
    public void setGrades(List<Grade> grades) {
        this.grades.setGrades(grades);
    }

    /**
     * Resets the existing data of this {@code GradeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyGradeBook newData) {
        requireNonNull(newData);

        setGrades(newData.getGradeList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code appointment} exists in the appointment book.
     */
    public boolean hasGrade(Grade grade) {
        requireNonNull(grade);
        return grades.contains(grade);
    }

    /**
     * Adds a person to the grade book.
     * The person must not already exist in the grade book.
     */
    public void addGrade(Grade g) {
        grades.add(g);
    }

    /**
     * Replaces the given grade {@code target} in the list with {@code editedGrade}.
     * {@code target} must exist in the grade book.
     * The person identity of {@code editedGrade} must not be the same as another existing grade
     * in the grade book.
     */
    public void setGrade(Grade target, Grade editedGrade) {
        requireNonNull(editedGrade);

        grades.setGrade(target, editedGrade);
    }

    /**
     * Removes {@code key} from this {@code GradeBook}.
     * {@code key} must exist in the grade book.
     */
    public void removeGrade(Grade key) {
        grades.remove(key);
    }

    /**
     * Removes {@code key} from this {@code GradeBook}.
     * {@code key} must exist in the grade book.
     */
    public void removeGrade(int key) {
        grades.remove(key);
    }
    //// util methods

    @Override
    public String toString() {
        return grades.asUnmodifiableObservableList().size() + " grades";
        // TODO: refine later
    }

    @Override
    public ObservableList<Grade> getGradeList() {
        return grades.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeBook // instanceof handles nulls
                && grades.equals(((GradeBook) other).grades));
    }

    @Override
    public int hashCode() {
        return grades.hashCode();
    }
}
