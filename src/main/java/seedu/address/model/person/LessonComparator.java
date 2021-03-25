package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import seedu.address.model.lesson.Lesson;

/**
 * Compares two {@code Person}s according to the order of their {@code Lesson}.
 */
public class LessonComparator implements Comparator<Person> {
    public LessonComparator(){}
    @Override
    public int compare(Person p1, Person p2) {

        ArrayList<Lesson> thisLessonList = new ArrayList<>(p1.getLessons());
        ArrayList<Lesson> otherLessonList = new ArrayList<>(p2.getLessons());

        if (thisLessonList.isEmpty() && !otherLessonList.isEmpty()) {
            return 1;
        } else if (!thisLessonList.isEmpty() && otherLessonList.isEmpty()) {
            return -1;
        } else if (thisLessonList.isEmpty() && otherLessonList.isEmpty()) {
            return 0;
        }

        Collections.sort(thisLessonList);
        Collections.sort(otherLessonList);

        Lesson thisLesson = thisLessonList.get(0);
        Lesson otherLesson = otherLessonList.get(0);
        return thisLesson.compareTo(otherLesson);
    }
}

