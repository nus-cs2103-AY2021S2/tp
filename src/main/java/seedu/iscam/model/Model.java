package seedu.iscam.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Client> PREDICATE_SHOW_ALL_CLIENTS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;

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
     * Returns true if a meeting with the same identity as {@code meeting} exists in the iscam book.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Deletes the given meeting.
     * The meeting must exist in the iscam book.
     */
    void deleteMeeting(Meeting target);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in the iscam book.
     */
    void addMeeting(Meeting meeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the iscam book.
     * The identity of {@code editedMeeting} must not be the same as another existing meeting in the iscam book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    /**
     * Returns an unmodifiable view of the filtered meeting list
     */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the iscam book.
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
     * Updates the filter of the filtered client list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClientList(Predicate<Client> predicate);

    /**
     * Returns an unmodifiable view of a client to be displayed in detail
     */
    ObservableClient getDetailedClient();

    /**
     * Updates the detailed client to match the supplied client.
     */
    void setDetailedClient(Client client);
}
