package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.lesson.exceptions.DuplicateLessonException;
import seedu.address.model.lesson.exceptions.LessonNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of lessons that enforces uniqueness between its elements and does not allow nulls.
 * A lesson is considered unique by comparing using {@code Lesson#isSameLesson(Lesson)}. As such, adding and updating of
 * persons uses Lesson#isSameLesson(Lesson) for equality so as to ensure that the lesson being added or updated is
 * unique in terms of timing and day in the UniqueLessonList. However, the removal of a lesson uses
 * Lesson#equals(Object) so as to ensure that the lesson with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueLessonList implements Iterable<Lesson> {

    private final ObservableList<Lesson> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent lesson as the given argument.
     */
    public boolean contains(Lesson toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLesson);
    }

    /**
     * Adds a lesson to the list.
     * The lesson must not already exist in the list.
     */
    public void add(Lesson toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLessonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in the list.
     * The lesson day and time of {@code editedLesson} must not be the same as another existing lesson in the list.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LessonNotFoundException();
        }

        if (!target.isSameLesson(editedLesson) && contains(editedLesson)) {
            throw new DuplicateLessonException();
        }

        internalList.set(index, editedLesson);
    }

    public Lesson getLesson(Lesson lesson) throws LessonNotFoundException {
        requireNonNull(lesson);
        if (contains(lesson)) {
            int index = internalList.indexOf(lesson);
            return internalList.get(index);
        }
        throw new LessonNotFoundException();
    }

    /**
     * Add a person {@code person} to the lesson in the list.
     * The person {@code person} must not be the same as another existing person in the lesson's set of persons.
     */
    public void addPersonToLesson(Person person) {
        requireAllNonNull(person);
        for (Lesson lesson : person.getLessons()) {
            if (contains(lesson)) {
                int index = internalList.indexOf(lesson);
                internalList.get(index).addPerson(person);
            } else {
                if (!lesson.containsPerson(person)) {
                    lesson.addPerson(person);
                }
                internalList.add(lesson);
            }
        }

    }

    /**
     * Removes a person {@code person} to the lesson in the list.
     */
    public void removePersonFromLesson(Person person) {
        requireNonNull(person);
        for (Lesson lesson : person.getLessons()) {
            if (contains(lesson)) {
                int index = internalList.indexOf(lesson);
                internalList.get(index).removePerson(person);
                if (internalList.get(index).isEmptyLesson()) {
                    remove(internalList.get(index));
                }
            }
        }
    }

    /**
     * Removes the equivalent lesson from the list.
     * The lesson must exist in the list.
     */
    public void remove(Lesson toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LessonNotFoundException();
        }
    }

    public void setLessons(UniqueLessonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        requireAllNonNull(lessons);
        if (!lessonsAreUnique(lessons)) {
            throw new DuplicateLessonException();
        }

        internalList.setAll(lessons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lesson> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lesson> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLessonList // instanceof handles nulls
                        && internalList.equals(((UniqueLessonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code lessons} contains only unique lessons.
     */
    private boolean lessonsAreUnique(List<Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).isSameLesson(lessons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
