package seedu.iscam.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.model.client.Client;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' iscam book file path.
     */
    Path getClientBookFilePath();

    /**
     * Sets the user prefs' iscam book file path.
     */
    void setClientBookFilePath(Path clientBookFilePath);

    /**
     * Returns the ClientBook
     */
    ReadOnlyClientBook getClientBook();

    /**
     * Replaces iscam book data with the data in {@code clientBook}.
     */
    void setClientBook(ReadOnlyClientBook clientBook);

    /**
     * Returns true if a client with the same identity as {@code client} exists in the iscam book.
     */
    boolean hasClient(Client client);

    /**
     * Deletes the given client.
     * The client must exist in the iscam book.
     */
    void deleteClient(Client target);

    /**
     * Adds the given client.
     * {@code client} must not already exist in the iscam book.
     */
    void addClient(Client client);

    /**
     * Replaces the given client {@code target} with {@code editedClient}.
     * {@code target} must exist in the iscam book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the iscam book.
     */
    void setClient(Client target, Client editedClient);

    /**
     * Returns an unmodifiable view of the filtered client list
     */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns an unmodifiable view of a client to be displayed in detail
     */
    ObservableClient getDetailedClient();

    /**
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);
}
