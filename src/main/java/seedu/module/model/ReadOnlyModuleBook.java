package seedu.module.model;

import javafx.collections.ObservableList;
import seedu.module.model.task.Module;
import seedu.module.model.task.Task;

/**
 * Unmodifiable view of an module book
 */
public interface ReadOnlyModuleBook {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the modules list.
     * This list will not contain any duplicate modules.
     */
    ObservableList<Module> getModuleList();

}
