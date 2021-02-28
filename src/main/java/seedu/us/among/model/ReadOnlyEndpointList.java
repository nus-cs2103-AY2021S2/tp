package seedu.us.among.model;

import javafx.collections.ObservableList;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Unmodifiable view of an API endpoint list
 */
public interface ReadOnlyEndpointList {

    /**
     * Returns an unmodifiable view of the API endpoint list.
     * This list will not contain any duplicate API endpoints.
     */
    ObservableList<Endpoint> getEndpointList();

}
