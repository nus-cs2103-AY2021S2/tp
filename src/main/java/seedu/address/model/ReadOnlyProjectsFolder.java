package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.project.Project;

/**
 * Unmodifiable view of the projects folder.
 */
public interface ReadOnlyProjectsFolder {

    /**
     * Returns an unmodifiable view of the projects folder.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectsList();

}
