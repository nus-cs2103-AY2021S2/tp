package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.lesson.Lesson;



/**
 * Compares two {@code Lesson}s according to the order of their {@code Time}.
 */
public class LessonTimeComparator implements Comparator<Lesson> {
    public LessonTimeComparator() {}

    @Override
    public int compare(Lesson l1, Lesson l2) {
        return l1.getTime().compareTo(l2.getTime());
    }
}
