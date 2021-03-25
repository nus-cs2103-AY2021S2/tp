package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTaskTracker {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

}
