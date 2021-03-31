package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.UniqueLessonList;
import seedu.address.model.person.Person;

public class LessonBook implements ReadOnlyLessonBook {

    private final UniqueLessonList lessons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        lessons = new UniqueLessonList();
    }

    public LessonBook() {}

    /**
     * Creates a LessonBook using the Lessons in the {@code toBeCopied}
     */
    public LessonBook(ReadOnlyLessonBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the lessons list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Resets the existing data of this {@code LessonBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLessonBook newData) {
        requireNonNull(newData);

        setLessons(newData.getLessonList());
    }

    //// importantDates-level operations

    /**
     * Returns true if a lesson with the same day and time as {@code lesson} exists in the
     * lesson book.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson);
    }

    public Lesson getLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.getLesson(lesson);
    }

    /**
     * Adds a lesson to the lesson book.
     * The lesson must not already exist in the lesson book.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Adds a person to his/her lessons.
     * The person must not already exist in the lesson.
     */
    public void addPersonToLesson(Person person) {
        lessons.addPersonToLesson(person);
    }

    /**
     * Removes a person from his/her lessons.
     * The person must already exist in the lesson.
     */
    public void removePersonFromLesson(Person person) {
        lessons.removePersonFromLesson(person);
    }

    /**
     * Removes {@code key} from this {@code LessonBook}.
     * {@code key} must exist in the lesson book.
     */
    public void removeLesson(Lesson key) {
        lessons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return lessons.asUnmodifiableObservableList().size() + " lessons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonBook // instanceof handles nulls
                && lessons.equals(((LessonBook) other).lessons));
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}
