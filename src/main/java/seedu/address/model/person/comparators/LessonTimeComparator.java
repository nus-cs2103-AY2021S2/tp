package seedu.address.model.person.comparators;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ModelManager;
import seedu.address.model.lesson.Lesson;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Compares two {@code Lesson}s according to the order of their {@code Time}.
 */
public class LessonTimeComparator implements Comparator<Lesson> {
    public LessonTimeComparator() {}

    @Override
    public int compare(Lesson l1, Lesson l2) {
        Logger logger = LogsCenter.getLogger(LessonTimeComparator.class);
        return l1.getTime().compareTo(l2.getTime());
    }
}
