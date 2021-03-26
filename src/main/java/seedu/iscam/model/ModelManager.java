package seedu.iscam.model;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;

/**
 * Represents the in-memory model of the iscam book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClientBook clientBook;
    private final FilteredList<Client> filteredClients;
    private final ObservableClient detailedClient;
    private final SimpleBooleanProperty isClientMode;

    private final MeetingBook meetingBook;
    private final FilteredList<Meeting> filteredMeetings;

    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given clientBook and userPrefs.
     */
    public ModelManager(ReadOnlyClientBook clientBook, ReadOnlyMeetingBook meetingBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(clientBook, userPrefs);

        logger.fine("Initializing with iscam book: " + clientBook + " and user prefs " + userPrefs);

        this.clientBook = new ClientBook(clientBook);
        this.filteredClients = new FilteredList<>(this.clientBook.getClientList());
        this.detailedClient = new ObservableClient();
        this.isClientMode = new SimpleBooleanProperty(true);

        this.meetingBook = new MeetingBook(meetingBook);
        this.filteredMeetings = new FilteredList<>(this.meetingBook.getMeetingList());

        this.userPrefs = new UserPrefs(userPrefs);
    }

    public ModelManager() {
        this(new ClientBook(), new MeetingBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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
    public Path getClientBookFilePath() {
        return userPrefs.getClientBookFilePath();
    }

    @Override
    public void setClientBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setClientBookFilePath(addressBookFilePath);
    }

    //=========== ClientBook ================================================================================

    @Override
    public ReadOnlyClientBook getClientBook() {
        return clientBook;
    }

    @Override
    public void setClientBook(ReadOnlyClientBook addressBook) {
        this.clientBook.resetData(addressBook);
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clientBook.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        clientBook.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        clientBook.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        clientBook.setClient(target, editedClient);
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetingBook.hasMeeting(meeting);
    }

    @Override
    public void deleteMeeting(Meeting target) {
        meetingBook.removeMeeting(target);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS); // May need adjust this
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);
        meetingBook.setMeeting(target, editedMeeting);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public ObservableClient getDetailedClient() {
        return detailedClient;
    }

    @Override
    public void setDetailedClient(Client client) {
        detailedClient.setClient(client);
    }

    //=========== Filtered Meeting List Accessors =============================================================

    @Override
    public MeetingBook getMeetingBook() {
        return meetingBook;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    /**
     * Set isClientMode to true.
     */
    @Override
    public void setClientMode() {
        isClientMode.set(true);
    }

    /**
     * Set isClientMode to false.
     */
    @Override
    public void setMeetingMode() {
        isClientMode.set(false);
    }

    /**
     * Set isClientMode to false.
     */
    @Override
    public ObservableValue<Boolean> getIsClientMode() {
        return isClientMode;
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
        return clientBook.equals(other.clientBook)
                && meetingBook.equals(other.meetingBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients);
    }

}
