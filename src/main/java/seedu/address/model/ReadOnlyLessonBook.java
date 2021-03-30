package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.lesson.Lesson;

/**
 * Unmodifiable view of a lesson book
 */

public interface ReadOnlyLessonBook {

    /**
     * Returns an unmodifiable view of the lessons list.
     * This list will not contain any duplicate lessons.
     */
    ObservableList<Lesson> getLessonList();

}
