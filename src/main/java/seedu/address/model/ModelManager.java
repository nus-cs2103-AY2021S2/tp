package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.AliasMapping;
import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.undoredo.StatefulAddressBook;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StatefulAddressBook statefulAddressBook;
    private final UserPrefs userPrefs;
    private final CommandHistory commandHistory;
    private final FilteredList<Resident> filteredResidents;
    private final FilteredList<Room> filteredRooms;
    private final FilteredList<ResidentRoom> filteredResidentRooms;
    private final FilteredList<Issue> filteredIssues;
    private final AliasMapping aliasMapping;

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs and commandHistory.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyCommandHistory commandHistory) {
        super();
        requireAllNonNull(addressBook, userPrefs, commandHistory);

        logger.fine("Initializing with data file: " + addressBook + ", user prefs " + userPrefs
                + " and command history " + commandHistory);

        this.statefulAddressBook = new StatefulAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredResidents = new FilteredList<>(this.statefulAddressBook.getResidentList());
        filteredRooms = new FilteredList<>(this.statefulAddressBook.getRoomList());
        filteredResidentRooms = new FilteredList<>(this.statefulAddressBook.getResidentRoomList());
        filteredIssues = new FilteredList<>(this.statefulAddressBook.getIssueList());

        this.aliasMapping = this.statefulAddressBook.getAliasMapping();

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
        this.statefulAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return statefulAddressBook;
    }

    // =========== Resident =============================================================

    @Override
    public boolean hasResident(Resident resident) {
        requireNonNull(resident);
        return statefulAddressBook.hasResident(resident);
    }

    @Override
    public boolean hasResident(Name name) {
        requireNonNull(name);
        return statefulAddressBook.hasResident(name);
    }

    @Override
    public void deleteResident(Resident target) {
        statefulAddressBook.removeResident(target);
    }

    @Override
    public void addResident(Resident resident) {
        statefulAddressBook.addResident(resident);
        updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
    }

    @Override
    public void setResident(Resident target, Resident editedResident) {
        requireAllNonNull(target, editedResident);

        statefulAddressBook.setResident(target, editedResident);
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

    /**
     * Checks if the {@code Model} contains a specified {@code Room}.
     *
     * @param room {@code Room} whose existence needs to be checked for in the {@code Model}.
     * @return True if {@code room} is in the {@code Model}.
     */
    @Override
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return statefulAddressBook.hasRoom(room);
    }

    /**
     * Checks a room with the same room number as {@code roomNumber} exists in SunRez.
     *
     * @param roomNumber {@code RoomNumber} used to check if a {@code Room} with the same {@code RoomNumber} exists in
     *                   the {@code Model}.
     * @return True if a room with the same room number as {@code roomNumber} exists in SunRez.
     * @throws NullPointerException If {@code RoomNumber} is null.
     */
    @Override
    public boolean hasRoom(RoomNumber roomNumber) {
        requireNonNull(roomNumber);
        return statefulAddressBook.hasRoom(roomNumber);
    }

    /**
     * Adds a {@code Room} to the {@code Model}.
     *
     * @param room {@code Room} to add.
     */
    @Override
    public void addRoom(Room room) {
        assert room != null;

        statefulAddressBook.addRoom(room);
        updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
    }

    /**
     * Deletes a {@code Room} in the {@code Model}.
     *
     * @param target {@code Room} to delete.
     */
    @Override
    public void deleteRoom(Room target) {
        assert target != null;

        statefulAddressBook.removeRoom(target);
    }

    /**
     * Replaces a given {@code Room} with another.
     *
     * @param target     {@code Room} to replace.
     * @param editedRoom {@code Room} to replace {@code target} with.
     * @throws NullPointerException If {@code target} or {@code editedRoom} is null.
     */
    @Override
    public void setRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);

        statefulAddressBook.setRoom(target, editedRoom);
    }

    /**
     * Gets the index of the matching room in the list with a given roomNumber.
     *
     * @param roomNumber {@code RoomNumber} used to check for a matching {@code Room}.
     * @return The {@code Index} of the {@code Room} matched by {@code RoomNumber}.
     */
    @Override
    public Index getIndexOfRoomWithSameRoomNumber(RoomNumber roomNumber) {
        List<Room> roomList = getAddressBook().getRoomList();
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomNumber().equals(roomNumber)) {
                return Index.fromZeroBased(i);
            }
        }
        return Index.fromZeroBased(-1);
    }

    /**
     * Gets the room with the matching room number in the list.
     *
     * @param roomNumber {@code RoomNumber} used to check for a matching {@code Room}.
     * @return The {@code Room} matched by {@code RoomNumber}.
     */
    public Room getRoomWithSameRoomNumber(RoomNumber roomNumber) {
        List<Room> roomList = getAddressBook().getRoomList();
        Index index = getIndexOfRoomWithSameRoomNumber(roomNumber);
        Room room = roomList.get(index.getZeroBased());
        return room;
    }

    // =========== Filtered Room List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Room} backed by the internal list of
     * {@code versionedAddressBook}.
     *
     * @return
     */
    @Override
    public ObservableList<Room> getFilteredRoomList() {
        return filteredRooms;
    }

    /**
     * Updates the filter of the filtered rooms list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        requireNonNull(predicate);
        filteredRooms.setPredicate(predicate);
    }

    // =========== ResidentRoom =============================================================

    @Override
    public boolean hasEitherResidentRoom(ResidentRoom residentRoom) {
        requireNonNull(residentRoom);
        return statefulAddressBook.hasEitherResidentRoom(residentRoom);
    }

    @Override
    public boolean hasBothResidentRoom(ResidentRoom residentRoom) {
        requireNonNull(residentRoom);
        return statefulAddressBook.hasBothResidentRoom(residentRoom);
    }

    @Override
    public void addResidentRoom(ResidentRoom residentRoom) {
        statefulAddressBook.addResidentRoom(residentRoom);
        updateFilteredResidentRoomList(PREDICATE_SHOW_ALL_RESIDENTROOMS);
    }

    @Override
    public void deleteResidentRoom(ResidentRoom target) {
        statefulAddressBook.removeResidentRoom(target);
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
    public boolean hasIssue(Issue issue) {
        return statefulAddressBook.hasIssue(issue);
    }

    @Override
    public void deleteIssue(Issue target) {
        statefulAddressBook.removeIssue(target);
    }

    @Override
    public void addIssue(Issue issue) {
        statefulAddressBook.addIssue(issue);
        updateFilteredIssueList(PREDICATE_SHOW_ALL_ISSUES);
    }

    @Override
    public void setIssue(Issue target, Issue editedIssue) {
        requireAllNonNull(target, editedIssue);

        statefulAddressBook.setIssue(target, editedIssue);
    }

    @Override
    public void closeIssue(Issue target) {
        Issue closedIssue = Issue.closeIssue(target);

        setIssue(target, closedIssue);
    }

    @Override
    public boolean issuesContainRoom(Room target) {
        return statefulAddressBook.issuesContainRoom(target);
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
        return statefulAddressBook.equals(other.statefulAddressBook)
                && userPrefs.equals(other.userPrefs)
                && commandHistory.equals(other.commandHistory)
                && filteredResidents.equals(other.filteredResidents)
                && filteredRooms.equals(other.filteredRooms)
                && filteredIssues.equals(other.filteredIssues)
                && aliasMapping.equals(other.aliasMapping);
    }

    // =========== Alias =============================================================
    @Override
    public AliasMapping getAliasMapping() {
        return statefulAddressBook.getAliasMapping();
    }

    @Override
    public void setAliasMapping(AliasMapping aliasMapping) {
        requireNonNull(aliasMapping);
        statefulAddressBook.setAliasMapping(aliasMapping);
    }

    @Override
    public void addAlias(Alias alias) {
        statefulAddressBook.addAlias(alias);
    }

    @Override
    public void deleteAlias(String aliasName) {
        statefulAddressBook.deleteAlias(aliasName);
    }

    // =========== Undo/Redo =============================================================
    @Override
    public boolean canUndoAddressBook() {
        return statefulAddressBook.canUndo();
    }

    @Override
    public void undoAddressBook() {
        statefulAddressBook.undo();
    }

    @Override
    public void commitAddressBook() {
        statefulAddressBook.commit();
    }

    @Override
    public boolean canRedoAddressBook() {
        return statefulAddressBook.canRedo();
    }

    @Override
    public void redoAddressBook() {
        statefulAddressBook.redo();
    }
}
