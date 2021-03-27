package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Task;

/**
 * Comparator that compares tasks according to their module codes.
 */
public class TaskNameComparator implements Comparator<Task> {

    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getTaskName().compareTo(secondTask.getTaskName());
    }
}
