package seedu.address.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of the RemindMe App.
 */
public interface ReadOnlyRemindMe {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     * @return
     */
    List<Module> getModuleList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
}
