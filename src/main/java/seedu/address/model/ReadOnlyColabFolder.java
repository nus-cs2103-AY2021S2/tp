package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;

/**
 * Unmodifiable view of a CoLAB folder.
 */
public interface ReadOnlyColabFolder {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Contact> getPersonList();

    /**
     * Returns an unmodifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectsList();

}
