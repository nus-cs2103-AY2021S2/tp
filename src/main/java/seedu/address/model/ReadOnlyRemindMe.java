package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of the RemindMe App.
 */
public interface ReadOnlyRemindMe {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Module> getModuleList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate general events.
     */
    ObservableList<GeneralEvent> getEventList();
}
