package seedu.us.among.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.us.among.commons.core.GuiSettings;
import seedu.us.among.model.endpoint.Endpoint;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Endpoint> PREDICATE_SHOW_ALL_ENDPOINTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' API endpoint list file path.
     */
    Path getEndpointListFilePath();

    /**
     * Sets the user prefs' API endpoint list file path.
     */
    void setEndpointListFilePath(Path endpointListFilePath);

    /**
     * Replaces API endpoint list data with the data in {@code endpointList}.
     */
    void setEndpointList(ReadOnlyEndpointList endpointList);

    /** Returns the EndpointList */
    ReadOnlyEndpointList getEndpointList();

    /** Returns true if there is no endpoints in EndpointList */
    boolean isEndpointListEmpty();

    /**
     * Returns true if a endpoint with the same identity as {@code endpoint} exists in the API endpoint list.
     */
    boolean hasEndpoint(Endpoint endpoint);

    /**
     * Removes the given endpoint.
     * The endpoint must exist in the API endpoint list.
     */
    void removeEndpoint(Endpoint target);

    /**
     * Adds the given endpoint.
     * {@code endpoint} must not already exist in the API endpoint list.
     */
    void addEndpoint(Endpoint endpoint);

    /**
     * Replaces the given endpoint {@code target} with {@code editedEndpoint}.
     * {@code target} must exist in the API endpoint list.
     * The endpoint identity of {@code editedEndpoint} must not be the same as another existing endpoint in the
     * API endpoint list.
     */
    void setEndpoint(Endpoint target, Endpoint editedEndpoint);

    /** Returns an unmodifiable view of the filtered endpoint list */
    ObservableList<Endpoint> getFilteredEndpointList();

    /**
     * Updates the filter of the filtered endpoint list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEndpointList(Predicate<Endpoint> predicate);
}
