package seedu.iscam.model;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.user.ReadOnlyUserPrefs;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.clientbook.ObservableClient;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.model.util.meetingbook.ObservableMeeting;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;

/**
 * Represents the in-memory model of the iscam book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ClientBook clientBook;
    private final FilteredList<Client> filteredClients;
    private final ObservableClient detailedClient;

    private final MeetingBook meetingBook;
    private final FilteredList<Meeting> filteredMeetings;
    private final ObservableMeeting detailedMeeting;

    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given clientBook and userPrefs.
     */
    public ModelManager(ReadOnlyClientBook clientBook, ReadOnlyMeetingBook meetingBook,
                        ObservableClient observableClient,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(clientBook, meetingBook, userPrefs);

        logger.fine("Initializing with iscam book: " + clientBook
                + ", meeting book " + meetingBook
                + " and user prefs " + userPrefs);

        this.clientBook = new ClientBook(clientBook);
        this.filteredClients = new FilteredList<>(this.clientBook.getClientList());
        this.detailedClient = new ObservableClient(observableClient);

        this.meetingBook = new MeetingBook(meetingBook);
        this.filteredMeetings = new FilteredList<>(this.meetingBook.getMeetingList());
        this.detailedMeeting = new ObservableMeeting();

        this.userPrefs = new UserPrefs(userPrefs);
    }

    public ModelManager(ReadOnlyClientBook clientBook, ReadOnlyMeetingBook meetingBook, ReadOnlyUserPrefs userPrefs) {
        this(clientBook, meetingBook, new ObservableClient(), userPrefs);
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
    public void setClientBookFilePath(Path clientBookFilePath) {
        requireNonNull(clientBookFilePath);
        userPrefs.setClientBookFilePath(clientBookFilePath);
    }

    @Override
    public Path getMeetingBookFilePath() {
        return userPrefs.getMeetingBookFilePath();
    }

    @Override
    public void setMeetingBookFilePath(Path meetingBookFilePath) {
        requireNonNull(meetingBookFilePath);
        userPrefs.setMeetingBookFilePath(meetingBookFilePath);
    }

    //=========== ClientBook ================================================================================

    @Override
    public ReadOnlyClientBook getClientBook() {
        return clientBook;
    }

    @Override
    public void setClientBook(ReadOnlyClientBook clientBook) {
        this.clientBook.resetData(clientBook);
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
    public void addClientAtIndex(Index index, Client client) {
        clientBook.addClientAtIndex(index, client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);
        clientBook.setClient(target, editedClient);
    }

    //=========== MeetingBook ================================================================================

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return meetingBook;
    }

    @Override
    public void setMeetingBook(ReadOnlyMeetingBook meetingBook) {
        this.meetingBook.resetData(meetingBook);
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetingBook.hasMeeting(meeting);
    }

    @Override
    public boolean hasConflictingMeetingWith(Meeting meeting, Meeting... exclusions) {
        requireNonNull(meeting);
        return meetingBook.hasConflictingMeetingWith(meeting, exclusions);
    }

    @Override
    public void deleteMeeting(Meeting target) {
        meetingBook.removeMeeting(target);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingBook.addMeeting(meeting);
        updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);
        meetingBook.setMeeting(target, editedMeeting);
    }


    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedClientBook}
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

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedClientBook}
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

    //TODO: header
    @Override
    public ObservableMeeting getDetailedMeeting() {
        return detailedMeeting;
    }

    //TODO: header
    @Override
    public void setDetailedMeeting(Meeting meeting) {
        detailedMeeting.setMeeting(meeting);

    }

    public Index getIndexOfClient(Client client) {
        return Index.fromZeroBased(filteredClients.indexOf(client));
    }

    //TODO: header
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
