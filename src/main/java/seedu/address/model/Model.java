package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.AliasMapping;
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
    /**
     * {@code Predicate} that always evaluate to true
     */
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
     * The resident identity of {@code editedResident} must not be the same as another existing resident in the address
     * book.
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
     * Checks if the {@code Model} contains a specified {@code Room}.
     *
     * @param room {@code Room} whose existence needs to be checked for in the {@code Model}.
     * @return True if a room with the same room number as {@code room} exists in SunRez.
     */
    boolean hasRoom(Room room);

    /**
     * Checks a room with the same room number as {@code roomNumber} exists in SunRez.
     *
     * @param roomNumber {@code RoomNumber} used to check if a {@code Room} with the same {@code RoomNumber} exists in
     *                   the {@code Model}.
     * @return True if a room with the same room number as {@code roomNumber} exists in SunRez.
     */
    boolean hasRoom(RoomNumber roomNumber);

    /**
     * Deletes a {@code Room} in the {@code Model}.
     *
     * @param target {@code Room} to delete.
     */
    void deleteRoom(Room target);

    /**
     * Adds a {@code Room} to the {@code Model}.
     *
     * @param room {@code Room} to add.
     */
    void addRoom(Room room);

    /**
     * Gets the room with the matching room number in the list.
     *
     * @param roomNumber {@code RoomNumber} used to check for a matching {@code Room}.
     * @return The {@code Room} matched by {@code RoomNumber}.
     */
    Room getRoomWithSameRoomNumber(RoomNumber roomNumber);

    /**
     * Gets the index of the matching room in the list with a given roomNumber.
     *
     * @param roomNumber {@code RoomNumber} used to check for a matching {@code Room}.
     * @return The {@code Index} of the {@code Room} matched by {@code RoomNumber}.
     */
    Index getIndexOfRoomWithSameRoomNumber(RoomNumber roomNumber);

    /**
     * Replaces a given {@code Room} with another.
     *
     * @param target     {@code Room} to replace.
     * @param editedRoom {@code Room} to replace {@code target} with.
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
     * Returns true if a issue with the same identity as {@code issue} exists in SunRez.
     *
     * @param issue Issue to be checked.
     * @return {@code True} if issue is in SunRez.
     * @throws NullPointerException If {@code issue} is null.
     */
    boolean hasIssue(Issue issue);

    /**
     * Deletes the given issue.
     * The issue must exist in SunRez.
     *
     * @param target Target issue to be deleted.
     * @throws NullPointerException If {@code target} is null.
     */
    void deleteIssue(Issue target);

    /**
     * Adds the given issue.
     *
     * @param issue Issue to be added.
     * @throws NullPointerException If {@code issue} is null.
     */
    void addIssue(Issue issue);

    /**
     * Replaces the given Issue {@code target} with {@code editedIssue}.
     * {@code target} must exist in the SunRez.
     *
     * @param target      Target issue to be replaced.
     * @param editedIssue Edited Issue to replace {@code target} with.
     * @throws NullPointerException If {@code target} or {@editIssue} is null.
     */
    void setIssue(Issue target, Issue editedIssue);

    /**
     * Closes the given issue.
     * The issue must exist in SunRez.
     *
     * @param target Target issue to be replaced.
     * @throws NullPointerException If {@code target} is null.
     */
    void closeIssue(Issue target);

    /**
     * Checks if any issues have the given room associated with it.
     *
     * @param target Room to check if it has issues associated with it.
     * @return {@code True} if there are issues with the given room associated with it.
     */
    boolean issuesContainRoom(Room target);

    /**
     * Returns an unmodifiable view of the filtered issue list.
     *
     * @return ObservableList of issues.
     */
    ObservableList<Issue> getFilteredIssueList();

    /**
     * Updates the filter of the filtered issue list to filter by the given {@code predicate}.
     *
     * @param predicate Predicate to filter the issue list.
     * @throws NullPointerException If {@code predicate} is null.
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
     * Returns an unmodifiable view of the filtered residentRoom list.
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
     *
     * @return The alias mapping.
     */
    AliasMapping getAliasMapping();

    /**
     * Sets the user's alias mapping.
     *
     * @param aliasMapping The specified mapping.
     */
    void setAliasMapping(AliasMapping aliasMapping);

    /**
     * Adds a user-defined alias to the current mapping.
     *
     * @param alias The Alias to be added.
     */
    void addAlias(Alias alias);

    /**
     * Returns true if the model has any previous states to restore. False otherwise.
     *
     * @return True if the model has any previous states to restore. False otherwise.
     */
    boolean canUndoAddressBook();

    /**
     * Restores the model's state to its previous state, if any.
     */
    void undoAddressBook();

    /**
     * Saves the model's state for undo.
     */
    void commitAddressBook();

    /**
     * Returns true if the model has any future states to restore. False otherwise.
     *
     * @return True if the model has any future states to restore. False otherwise.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's state to its next state, if any.
     */
    void redoAddressBook();

    /**
     * Deletes a user-defined alias from the current mapping.
     *
     * @param aliasName The name of the alias to be deleted.
     */
    void deleteAlias(String aliasName);
}
