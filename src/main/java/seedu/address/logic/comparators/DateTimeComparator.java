package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Task;

/**
 * Comparator that compares tasks according to their deadline dates and times.
 */
public class DateTimeComparator implements Comparator<Task> {

    @Override
    public int compare(Task firstTask, Task secondTask) {
        if (firstTask.getDeadlineDate().equals(secondTask.getDeadlineDate())) {
            return firstTask.getDeadlineTime().compareTo(secondTask.getDeadlineTime());
        } else {
            return firstTask.getDeadlineDate().compareTo(secondTask.getDeadlineDate());
        }
    }
}
