package seedu.address.logic.comparators;

import java.util.Comparator;

import seedu.address.model.person.Task;

/**
 * Comparator that compares tasks according to their module codes.
 */
public class ModuleCodeComparator implements Comparator<Task> {

    @Override
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.getModuleCode().compareTo(secondTask.getModuleCode());
    }
}
