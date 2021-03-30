package seedu.address.model.person.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

/**
 * Compares two {@code Person}s according to the order of their {@code Lesson}.
 */
public class PersonLessonComparator implements Comparator<Person> {
    public PersonLessonComparator(){}
    @Override
    public int compare(Person p1, Person p2) {

        ArrayList<Lesson> thisLessonList = new ArrayList<>(p1.getLessons());
        Collections.sort(thisLessonList);
        ArrayList<Lesson> otherLessonList = new ArrayList<>(p2.getLessons());
        Collections.sort(otherLessonList);

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


