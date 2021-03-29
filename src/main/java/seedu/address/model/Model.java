package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Resident> PREDICATE_SHOW_ALL_RESIDENTS = unused -> true;
    Predicate<Room> PREDICATE_SHOW_ALL_ROOMS = unused -> true;
    Predicate<Issue> PREDICATE_SHOW_ALL_ISSUES = unused -> true;
    Predicate<ResidentRoom> PREDICATE_SHOW_ALL_RESIDENTROOMS = unused -> true;

    // =========== UserPrefs ==================================================================================

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    // =========== AddressBook ================================================================================
    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    // =========== Resident =============================================================

    /**
     * Returns true if a resident with the same identity as {@code resident} exists in the address book.
     */
    boolean hasResident(Resident resident);

    boolean hasResident(Name name);

    /**
     * Deletes the given resident.
     * The resident must exist in the address book.
     */
    void deleteResident(Resident target);

    /**
     * Adds the given resident.
     * {@code resident} must not already exist in the address book.
     */
    void addResident(Resident resident);

    /**
     * Replaces the given resident {@code target} with {@code editedResident}.
     * {@code target} must exist in the address book.
     * The resident identity of {@code editedResident} must not be the same
     * as another existing resident in the address book.
     */
    void setResident(Resident target, Resident editedResident);

    /**
     * Gets the resident with the matching room number in the list.
     */
    Resident getResidentWithSameName(Name name);

    /**
     * Gets the index of the matching resident in the list
     * with a given name.
     */
    Index getIndexOfResidentWithSameName(Name name);

    /**
     * Returns an unmodifiable view of the filtered resident list
     */
    ObservableList<Resident> getFilteredResidentList();

    /**
     * Updates the filter of the filtered resident list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredResidentList(Predicate<Resident> predicate);

    // =========== Room =============================================================

    /**
     * Returns true if a room with the same room number as {@code room} exists in SunRez.
     */
    boolean hasRoom(Room room);

    /**
     * Returns true if a room with the same room number as {@code roomNumber} exists in SunRez.
     */
    boolean hasRoom(RoomNumber roomNumber);

    /**
     * Deletes the given room.
     * The room must exist in SunRez.
     */
    void deleteRoom(Room target);

    /**
     * Adds the given room.
     * {@code room} must not already exist in SunRez.
     */
    void addRoom(Room room);

    /**
     * Gets the room with the matching room number in the list.
     */
    Room getRoomWithSameRoomNumber(RoomNumber roomNumber);

    /**
     * Gets the index of the matching room in the list
     * with a given roomNumber.
     */
    Index getIndexOfRoomWithSameRoomNumber(RoomNumber roomNumber);

    /**
     * Replaces the given room {@code target} with {@code editedRoom}.
     * {@code target} must exist in SunRez.
     * The Room identity of {@code editedRoom} must not be the same as another existing room in SunRez.
     */
    void setRoom(Room target, Room editedRoom);

    /**
     * Returns an unmodifiable view of the filtered room list
     */
    ObservableList<Room> getFilteredRoomList();

    /**
     * Updates the filter of the filtered rooms list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoomList(Predicate<Room> predicate);

    /**
     * Returns an unmodifiable view of the command history.
     *
     * @return An unmodifiable view of the command history.
     */
    ReadOnlyCommandHistory getCommandHistory();

    /**
     * Appends a command history entry to the command history list.
     *
     * @param commandText The command text entry to append.
     */
    void appendCommandHistoryEntry(String commandText);

    /**
     * Deletes the given issue.
     * The issue must exist in SunRez.
     */
    void deleteIssue(Issue target);

    /**
     * Adds the given issue.
     */
    void addIssue(Issue issue);

    /**
     * Replaces the given Issue {@code target} with {@code editedIssue}.
     * {@code target} must exist in the address book.
     */
    void setIssue(Issue target, Issue editedIssue);


    /**
     * Returns an unmodifiable view of the filtered issue list.
     */
    ObservableList<Issue> getFilteredIssueList();

    /**
     * Updates the filter of the filtered issue list to filter by the given.
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIssueList(Predicate<Issue> predicate);

    // =========== ResidentRoom =============================================================
    /**
     * Returns true if a residentroom with the same identity as {@code residentRoom} exists in the address book.
     */
    boolean hasEitherResidentRoom(ResidentRoom residentRoom);

    boolean hasBothResidentRoom(ResidentRoom residentRoom);

    /**
     * Deletes the given residentRoom.
     * The residentRoom must exist in the address book.
     */
    void deleteResidentRoom(ResidentRoom target);

    /**
     * Adds the given residentroom.
     * {@code residentRoom} must not already exist in the address book.
     */
    void addResidentRoom(ResidentRoom residentRoom);

    /**
     * Replaces the given resident {@code target} with {@code editedResident}.
     * {@code target} must exist in the address book.
     * The resident identity of {@code editedResident} must not be the same
     * as another existing resident in the address book.
     */
    void setResidentRoom(ResidentRoom target, ResidentRoom editedResidentRoom);

    /**
     * Returns an unmodifiable view of the filtered resident list
     */
    ObservableList<ResidentRoom> getFilteredResidentRoomList();

    /**
     * Updates the filter of the filtered resident list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredResidentRoomList(Predicate<ResidentRoom> predicate);

    /**
     * Returns the current user's alias mapping.
     */
    AliasMapping getAliasMapping();

    /**
     * Set the user's alias mapping.
     */
    void setAliasMapping(AliasMapping aliasMapping);

    /**
     * Add an user-defined alias to the current mapping.
     */
    void addAlias(Alias alias);
}
