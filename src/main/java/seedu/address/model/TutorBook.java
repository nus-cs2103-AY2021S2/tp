package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.subject.TutorSubject;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Tutor;
import seedu.address.model.tutor.UniqueTutorList;

/**
 * Wraps all data at the tutor-book level
 * Duplicates are not allowed (by .isSameTutor comparison)
 */
public class TutorBook implements ReadOnlyTutorBook {

    private final UniqueTutorList tutors;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        tutors = new UniqueTutorList();
    }

    public TutorBook() {}

    /**
     * Creates a TutorBook using the tutors in the {@code toBeCopied}
     */
    public TutorBook(ReadOnlyTutorBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the tutor list with {@code tutors}.
     * {@code tutors} must not contain duplicate tutors.
     */
    public void setTutors(List<Tutor> tutors) {
        this.tutors.setTutors(tutors);
    }

    /**
     * Resets the existing data of this {@code TutorBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTutorBook newData) {
        requireNonNull(newData);

        setTutors(newData.getTutorList());
    }

    //// tutor-level operations

    /**
     * Returns true if a tutor with the same identity as {@code tutor} exists in the tutor book.
     */
    public boolean hasTutor(Tutor tutor) {
        requireNonNull(tutor);
        return tutors.contains(tutor);
    }

    /**
     * Adds a tutor to the tutor book.
     * The tutor must not already exist in the tutor book.
     */
    public void addTutor(Tutor p) {
        tutors.add(p);
    }

    /**
     * Replaces the given tutor {@code target} in the list with {@code editedTutor}.
     * {@code target} must exist in the tutor book.
     * The tutor identity of {@code editedTutor} must not be the same as another existing tutor in the tutor book.
     */
    public void setTutor(Tutor target, Tutor editedTutor) {
        requireNonNull(editedTutor);

        tutors.setTutor(target, editedTutor);
    }

    /**
     * Removes {@code key} from this {@code TutorBook}.
     * {@code key} must exist in the tutor book.
     */
    public void removeTutor(Tutor key) {
        tutors.remove(key);
    }

    /**
     * @param name Name of tutor.
     * @return True if list contains name of tutor.
     */
    public boolean containsTutorByName(Name name) {
        for (Tutor tutor : this.tutors) {
            if (tutor.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param name Name of tutor.
     * @param subjectName Subject name to teach.
     * @return True is tutor teaches this subject.
     */
    public boolean tutorTeachesSubject(Name name, SubjectName subjectName) {
        for (Tutor tutor : this.tutors) {
            if (tutor.getName().equals(name)) {
                SubjectList subjectList = tutor.getSubjectList();
                for (TutorSubject tutorSubject : subjectList) {
                    if (tutorSubject.getName().equals(subjectName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //// util methods

    @Override
    public String toString() {
        return tutors.asUnmodifiableObservableList().size() + " tutors";
        // TODO: refine later
    }

    @Override
    public ObservableList<Tutor> getTutorList() {
        return tutors.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorBook // instanceof handles nulls
                && tutors.equals(((TutorBook) other).tutors));
    }

    @Override
    public int hashCode() {
        return tutors.hashCode();
    }
}
