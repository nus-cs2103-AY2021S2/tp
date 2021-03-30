package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of HEY MATEz
 */
public interface ReadOnlyHeyMatez {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Task> getTaskList();

}
