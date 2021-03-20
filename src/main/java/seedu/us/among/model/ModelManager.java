package seedu.us.among.model;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * Represents the in-memory model of the API endpoint list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EndpointList endpointList;
    private final UserPrefs userPrefs;
    private final FilteredList<Endpoint> filteredEndpoints;

    /**
     * Initializes a ModelManager with the given API endpoint list and userPrefs.
     */
    public ModelManager(ReadOnlyEndpointList endpointList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(endpointList, userPrefs);

        logger.fine("Initializing with API endpoint list: " + endpointList + " and user prefs " + userPrefs);

        this.endpointList = new EndpointList(endpointList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredEndpoints = new FilteredList<>(this.endpointList.getEndpointList());
    }

    public ModelManager() {
        this(new EndpointList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEndpointListFilePath() {
        return userPrefs.getEndpointListFilePath();
    }

    @Override
    public void setEndpointListFilePath(Path endpointListFilePath) {
        requireNonNull(endpointListFilePath);
        userPrefs.setEndpointListFilePath(endpointListFilePath);
    }

    //=========== EndpointList ================================================================================

    @Override
    public void setEndpointList(ReadOnlyEndpointList endpointList) {
        this.endpointList.resetData(endpointList);
    }

    @Override
    public ReadOnlyEndpointList getEndpointList() {
        return endpointList;
    }

    @Override
    public boolean hasEndpoint(Endpoint endpoint) {
        requireNonNull(endpoint);
        return endpointList.hasEndpoint(endpoint);
    }

    @Override
    public void removeEndpoint(Endpoint target) {
        endpointList.removeEndpoint(target);
    }

    @Override
    public void addEndpoint(Endpoint endpoint) {
        endpointList.addEndpoint(endpoint);
        updateFilteredEndpointList(PREDICATE_SHOW_ALL_ENDPOINTS);
    }

    @Override
    public void setEndpoint(Endpoint target, Endpoint editedEndpoint) {
        requireAllNonNull(target, editedEndpoint);

        endpointList.setEndpoint(target, editedEndpoint);
    }

    @Override
    public boolean isEndpointListEmpty() {
        return endpointList.isEmpty();
    }

    //=========== Filtered Endpoint List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Endpoint} backed by the internal list of
     * {@code versionedimPoster}
     */
    @Override
    public ObservableList<Endpoint> getFilteredEndpointList() {
        return filteredEndpoints;
    }

    @Override
    public void updateFilteredEndpointList(Predicate<Endpoint> predicate) {
        requireNonNull(predicate);
        filteredEndpoints.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return endpointList.equals(other.endpointList)
                && userPrefs.equals(other.userPrefs)
                && filteredEndpoints.equals(other.filteredEndpoints);
    }

}
