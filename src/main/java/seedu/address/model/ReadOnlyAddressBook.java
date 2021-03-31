package seedu.address.model;

import javafx.collections.ObservableList;
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

}
