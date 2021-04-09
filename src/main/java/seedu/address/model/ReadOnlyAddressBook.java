package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.AliasMapping;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.Room;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Resident> getResidentList();

    /**
     * Returns an unmodifiable view of the rooms list.
     * This list will not contain any duplicate rooms.
     */
    ObservableList<Room> getRoomList();

    /**
     * Returns an unmodifiable view of the residentrooms list.
     * This list will not contain any duplicate residentrooms.
     */
    ObservableList<ResidentRoom> getResidentRoomList();

    /**
     * Returns an unmodifiable view of the issues list.
     */
    ObservableList<Issue> getIssueList();

    //=========== Alias =============================================================
    /**
     * Returns the current alias mapping.
     *
     * @return The mapping.
     */
    AliasMapping getAliasMapping();

    /**
     * Returns an Alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return The alias with the specified name.
     */
    Alias getAlias(String aliasName);

    /**
     * Checks if the current mapping contains an alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return Whether the mapping contains the alias.
     */
    boolean containsAlias(String aliasName);

    /**
     * Checks if the alias name is a reserved keyword.
     *
     * @param aliasName Name of the alias.
     * @return Whether the name is reserved.
     */
    boolean isReservedKeyword(String aliasName);

    /**
     * Checks if the command word is recursive.
     *
     * @param commandWord The command word.
     * @return Whether the command word is recursive.
     */
    boolean isRecursiveKeyword(String commandWord);
}
