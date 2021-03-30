package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an planner
 */
public interface ReadOnlyPlanner {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

    /**
     * Returns the UniqueTagList object so that the internal list and map of counts can be copied.
     * This list will not contain any duplicate tags.
     */
    UniqueTagList getUniqueTagListObject();
}
