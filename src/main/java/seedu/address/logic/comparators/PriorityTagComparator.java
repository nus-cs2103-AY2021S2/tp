package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Task;

/**
 * Comparator that compares tasks according to their priority tags.
 */
public class PriorityTagComparator implements Comparator<Task> {

    @Override
    public int compare(Task firstTask, Task secondTask) {
        return secondTask.getPriorityTag().getPriority() - firstTask.getPriorityTag().getPriority();
    }
}
