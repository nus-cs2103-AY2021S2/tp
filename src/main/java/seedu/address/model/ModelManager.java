package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Resident;
import seedu.address.model.room.Room;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final CommandHistory commandHistory;
    private final FilteredList<Resident> filteredResidents;
    private final FilteredList<Room> filteredRooms;
    private final FilteredList<Issue> filteredIssues;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs and commandHistory.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
            ReadOnlyCommandHistory commandHistory) {
        super();
        requireAllNonNull(addressBook, userPrefs, commandHistory);

        logger.fine("Initializing with address book: " + addressBook + ", user prefs " + userPrefs
                + " and command history " + commandHistory);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredResidents = new FilteredList<>(this.addressBook.getResidentList());
        filteredRooms = new FilteredList<>(this.addressBook.getRoomList());
        filteredIssues = new FilteredList<>(this.addressBook.getIssueList());
        this.commandHistory = new CommandHistory(commandHistory);
    }

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        this(addressBook, userPrefs, new CommandHistory());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // =========== UserPrefs ==================================================================================

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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // =========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasResident(Resident resident) {
        requireNonNull(resident);
        return addressBook.hasResident(resident);
    }

    @Override
    public void deleteResident(Resident target) {
        addressBook.removeResident(target);
    }

    @Override
    public void addResident(Resident resident) {
        addressBook.addResident(resident);
        updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
    }

    @Override
    public void setResident(Resident target, Resident editedResident) {
        requireAllNonNull(target, editedResident);

        addressBook.setResident(target, editedResident);
    }

    // =========== Filtered Resident List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Resident} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Resident> getFilteredResidentList() {
        return filteredResidents;
    }

    @Override
    public void updateFilteredResidentList(Predicate<Resident> predicate) {
        requireNonNull(predicate);
        filteredResidents.setPredicate(predicate);
    }

    // =========== Room =============================================================

    @Override
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return addressBook.hasRoom(room);
    }

    @Override
    public void addRoom(Room room) {
        addressBook.addRoom(room);
        updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
    }

    @Override
    public void deleteRoom(Room target) {
        addressBook.removeRoom(target);
    }

    @Override
    public void setRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);

        addressBook.setRoom(target, editedRoom);
    }

    // =========== Filtered Room List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Room} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Room> getFilteredRoomList() {
        return filteredRooms;
    }

    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        requireNonNull(predicate);
        filteredRooms.setPredicate(predicate);
    }

    // =========== Command History =============================================================
    @Override
    public ReadOnlyCommandHistory getCommandHistory() {
        return commandHistory;
    }

    @Override
    public void appendCommandHistoryEntry(String commandText) {
        commandHistory.appendEntry(new CommandHistoryEntry(commandText));
    }

    // =========== Issues =====================================================================================
    @Override
    public void deleteIssue(Issue target) {
        addressBook.removeIssue(target);
    }

    @Override
    public void addIssue(Issue issue) {
        addressBook.addIssue(issue);
        updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
    }

    @Override
    public void setIssue(Issue target, Issue editedIssue) {
        requireAllNonNull(target, editedIssue);

        addressBook.setIssue(target, editedIssue);
    }

    // =========== Filtered Issue List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Issue} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Issue> getFilteredIssueList() {
        return filteredIssues;
    }

    @Override
    public void updateFilteredIssueList(Predicate<Issue> predicate) {
        requireNonNull(predicate);
        filteredIssues.setPredicate(predicate);

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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && commandHistory.equals(other.commandHistory)
                && filteredResidents.equals(other.filteredResidents)
                && filteredRooms.equals(other.filteredRooms)
                && filteredIssues.equals(other.filteredIssues);
    }

    //=========== Alias =============================================================
    @Override
    public AliasMapping getAliasMapping() {
        return userPrefs.getAliasMapping();
    }

    @Override
    public void setAliasMapping(AliasMapping aliasMapping) {
        requireNonNull(aliasMapping);
        userPrefs.setAliasMapping(aliasMapping);
    }

    @Override
    public void addAlias(Alias alias) {
        userPrefs.addAlias(alias);
    }

    @Override
    public void deleteAlias(String aliasName) {
        userPrefs.deleteAlias(aliasName);
    }
}
