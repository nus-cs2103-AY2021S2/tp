package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;

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
    private final FilteredList<ResidentRoom> filteredResidentRooms;
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
        filteredResidentRooms = new FilteredList<>(this.addressBook.getResidentRoomList());
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

    // =========== Resident =============================================================

    @Override
    public boolean hasResident(Resident resident) {
        requireNonNull(resident);
        return addressBook.hasResident(resident);
    }

    @Override
    public boolean hasResident(Name name) {
        requireNonNull(name);
        return addressBook.hasResident(name);
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

    @Override
    public Index getIndexOfResidentWithSameName(Name name) {
        List<Resident> lastShownList = getFilteredResidentList();
        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getName().equals(name)) {
                return Index.fromZeroBased(i);
            }
        }
        return Index.fromZeroBased(-1);
    }

    public Resident getResidentWithSameName(Name name) {
        List<Resident> lastShownList = getFilteredResidentList();
        Index index = getIndexOfResidentWithSameName(name);
        Resident resident = lastShownList.get(index.getZeroBased());
        return resident;
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
    public boolean hasRoom(RoomNumber roomNumber) {
        requireNonNull(roomNumber);
        return addressBook.hasRoom(roomNumber);
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

    @Override
    public Index getIndexOfRoomWithSameRoomNumber(RoomNumber roomNumber) {
        List<Room> lastShownList = getFilteredRoomList();
        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getRoomNumber().equals(roomNumber)) {
                return Index.fromZeroBased(i);
            }
        }
        return Index.fromZeroBased(-1);
    }

    public Room getRoomWithSameRoomNumber(RoomNumber roomNumber) {
        List<Room> lastShownList = getFilteredRoomList();
        Index index = getIndexOfRoomWithSameRoomNumber(roomNumber);
        Room room = lastShownList.get(index.getZeroBased());
        return room;
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


    // =========== ResidentRoom =============================================================

    @Override
    public boolean hasEitherResidentRoom(ResidentRoom residentRoom) {
        requireNonNull(residentRoom);
        return addressBook.hasEitherResidentRoom(residentRoom);
    }

    @Override
    public boolean hasBothResidentRoom(ResidentRoom residentRoom) {
        requireNonNull(residentRoom);
        return addressBook.hasBothResidentRoom(residentRoom);
    }

    @Override
    public void addResidentRoom(ResidentRoom residentRoom) {
        addressBook.addResidentRoom(residentRoom);
        updateFilteredResidentRoomList(PREDICATE_SHOW_ALL_RESIDENTROOMS);
    }

    @Override
    public void deleteResidentRoom(ResidentRoom target) {
        addressBook.removeResidentRoom(target);
    }

    @Override
    public void setResidentRoom(ResidentRoom target, ResidentRoom editedResidentRoom) {
        requireAllNonNull(target, editedResidentRoom);

        addressBook.setResidentRoom(target, editedResidentRoom);
    }

    // =========== Filtered ResidentRoom List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code ResidentRoom} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<ResidentRoom> getFilteredResidentRoomList() {
        return filteredResidentRooms;
    }

    @Override
    public void updateFilteredResidentRoomList(Predicate<ResidentRoom> predicate) {
        requireNonNull(predicate);
        filteredResidentRooms.setPredicate(predicate);
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
}
