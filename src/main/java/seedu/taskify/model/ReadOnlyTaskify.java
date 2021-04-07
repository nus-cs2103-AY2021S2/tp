package seedu.taskify.model;

import javafx.collections.ObservableList;
import seedu.taskify.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTaskify {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();


}
