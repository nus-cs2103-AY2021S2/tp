package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueList;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.UniqueResidentList;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.residentroom.UniqueResidentRoomList;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.UniqueRoomList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameResident comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueResidentList residents;
    private final UniqueRoomList rooms;
    private final UniqueResidentRoomList residentRooms;
    private final IssueList issues;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        residents = new UniqueResidentList();
        rooms = new UniqueRoomList();
        residentRooms = new UniqueResidentRoomList();
        issues = new IssueList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Residents in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the resident list with {@code residents}.
     * {@code residents} must not contain duplicate residents.
     */
    public void setResidents(List<Resident> residents) {
        this.residents.setResidents(residents);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    public void setResidentRooms(List<ResidentRoom> residentRooms) {
        this.residentRooms.setResidentRooms(residentRooms);
    }

    /**
     * Replaces the contents of the issue list with {@code issues}.
     */
    public void setIssues(List<Issue> issues) {
        this.issues.setIssues(issues);
    }

    //// resident-level operations

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     * Returns true if a resident with the same identity as {@code resident} exists in the address book.
     */
    public boolean hasResident(Resident resident) {
        requireNonNull(resident);
        return residents.contains(resident);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     * Returns true if a resident with the same identity as {@code resident} exists in the address book.
     */
    public boolean hasResident(Name name) {
        requireNonNull(name);
        return residents.contains(name);
    }

    /**
     * Adds a resident to the address book.
     * The resident must not already exist in the address book.
     */
    public void addResident(Resident p) {
        residents.add(p);
    }

    /**
     * Replaces the given resident {@code target} in the list with {@code editedResident}.
     * {@code target} must exist in the address book.
     * The resident identity of {@code editedResident} must not be the same
     * as another existing resident in the address book.
     */
    public void setResident(Resident target, Resident editedResident) {
        requireNonNull(editedResident);

        residents.setResident(target, editedResident);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeResident(Resident key) {
        residents.remove(key);
    }

    //// room-level operations

    /**
     * Returns true if a room with the same identity as {@code room} exists SunRez.
     */
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return rooms.contains(room);
    }

    /**
     * Returns true if a room with the same identity as {@code room} exists SunRez.
     */
    public boolean hasRoom(RoomNumber roomNumber) {
        requireNonNull(roomNumber);
        return rooms.contains(roomNumber);
    }


    /**
     * Adds a room to SunRez.
     * The room must not already exist in SunRez.
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Replaces the given room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in SunRez.
     * The room identity of {@code editedRoom} must not be the same as another existing room in SunRez.
     */
    public void setRoom(Room target, Room editedRoom) {
        requireNonNull(editedRoom);

        rooms.setRoom(target, editedRoom);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code Room} must exist in SunRez.
     */
    public void removeRoom(Room key) {
        rooms.remove(key);
    }

    //// residentroom-level operations

    /**
     * Returns true if a residentroom with the same name or room number exists in SunRez.
     */
    public boolean hasEitherResidentRoom(ResidentRoom residentRoom) {
        requireNonNull(residentRoom);
        return residentRooms.containsEitherResidentRoom(residentRoom);
    }

    /**
     * Returns true if a residentroom with the same name and room number exists  in SunRez.
     */
    public boolean hasBothResidentRoom(ResidentRoom residentRoom) {
        requireNonNull(residentRoom);
        return residentRooms.containsBothResidentRoom(residentRoom);
    }

    /**
     * Adds a residentroom to SunRez.
     * The residentroom must not already exist in SunRez.
     */
    public void addResidentRoom(ResidentRoom residentRoom) {
        residentRooms.add(residentRoom);
    }

    /**
     * Replaces the given residentroom {@code target} in the list with {@code editedResidentRoom}.
     * {@code target} must exist in SunRez.
     * The residentRoom identity of {@code editedResidentRoom} must not be the same as
     * another existing residentroom in SunRez.
     */
    public void setResidentRoom(ResidentRoom target, ResidentRoom editedResidentRoom) {
        requireNonNull(editedResidentRoom);
        residentRooms.setResidentRoom(target, editedResidentRoom);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code ResidentRoom} must exist in SunRez.
     */
    public void removeResidentRoom(ResidentRoom key) {
        residentRooms.remove(key);
    }


    //// meta methods

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setResidents(newData.getResidentList());
        setRooms(newData.getRoomList());
        setResidentRooms(newData.getResidentRoomList());
        setIssues(newData.getIssueList());
    }

    //// issue-level operations

    /**
     * Adds a issue to the address book.
     * The issue must not already exist in the address book.
     */
    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    /**
     * Replaces the given issue {@code target} in the list with
     * {@code editedIssue}.
     * {@code target} must exist in the address book.
     * The issue identity of {@code editedIssue} must not be the same as another
     * existing issue in the address book.
     */
    public void setIssue(Issue target, Issue editedIssue) {
        requireNonNull(editedIssue);

        issues.setIssue(target, editedIssue);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeIssue(Issue key) {
        issues.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return residents.asUnmodifiableObservableList().size() + " residents\n"
                + rooms.asUnmodifiableObservableList().size() + " rooms\n"
                + issues.asUnmodifiableObservableList().size() + " issues";
        // TODO: refine later
    }

    @Override
    public ObservableList<Resident> getResidentList() {
        return residents.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Issue> getIssueList() {
        return issues.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<ResidentRoom> getResidentRoomList() {
        return residentRooms.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                        && residents.equals(((AddressBook) other).residents)
                        && rooms.equals(((AddressBook) other).rooms)
                        && issues.equals(((AddressBook) other).issues));
    }

    @Override
    public int hashCode() {
        return residents.hashCode();
    }
}
