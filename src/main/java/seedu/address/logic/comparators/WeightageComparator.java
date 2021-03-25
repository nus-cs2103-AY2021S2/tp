package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Task;

/**
 * Comparator that compares tasks according to their weightage.
 */
public class WeightageComparator implements Comparator<Task> {

    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getWeightage().compareTo(secondTask.getWeightage());
    }
}
