package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.AliasMapping;
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
    private final AliasMapping aliasMapping;

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
        aliasMapping = new AliasMapping();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Residents in the {@code toBeCopied}.
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
     * Returns true if a residentroom with the same name and room number exists in SunRez.
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
        setAliasMapping(newData.getAliasMapping());
    }

    //// issue-level operations

    /**
     * Returns true if a issue with the same identity as {@code issue} exists SunRez.
     *
     * @param issue The issue to check.
     * @return True if SunRez has the issue.
     * @throws NullPointerException If {@code issue} is null.
     */
    public boolean hasIssue(Issue issue) {
        return issues.contains(issue);
    }

    /**
     * Adds the given issue.
     *
     * @param issue The issue to add.
     * @throws NullPointerException If {@code issue} is null.
     */
    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    /**
     * Replaces the given issue {@code target} in the list with
     * {@code editedIssue}.
     * {@code target} must exist in SunRez.
     * The issue identity of {@code editedIssue} must not be the same as another
     * existing issue in SunRez.
     *
     * @param target      The target issue to replace.
     * @param editedIssue The replacement issue.
     * @throws NullPointerException If {@code target} or {@code editedIssue} is null.
     */
    public void setIssue(Issue target, Issue editedIssue) {
        issues.setIssue(target, editedIssue);
    }

    /**
     * Removes {@code key} from this SunRez.
     * {@code key} must exist in SunRez.
     *
     * @param key Issue to be removed.
     * @throws NullPointerException If {@code key} is null.
     */
    public void removeIssue(Issue key) {
        issues.remove(key);
    }

    /**
     * Checks if any issues have the given room associated with it.
     *
     * @param target Room to check if it has issues associated with it.
     * @return True if there are issues with the given room associated with it.
     */
    public boolean issuesContainRoom(Room target) {
        return issues.containsRoom(target);
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

    /**
     * Returns the current alias mapping.
     *
     * @return The mapping.
     */
    public AliasMapping getAliasMapping() {
        return this.aliasMapping;
    }

    /**
     * Sets the current mapping to the specified mapping.
     *
     * @param aliasMapping The mapping.
     * @throws NullPointerException If the input is null.
     */
    public void setAliasMapping(AliasMapping aliasMapping) {
        requireNonNull(aliasMapping);
        this.aliasMapping.setAliasMapping(aliasMapping);
    }

    /**
     * Adds a user-defined alias to the current mapping.
     *
     * @param alias The alias to be added.
     * @throws NullPointerException If the input is null.
     */
    public void addAlias(Alias alias) {
        requireNonNull(alias);
        this.aliasMapping.addAlias(alias);
    }

    /**
     * Deletes a user-defined alias from the current mapping.
     *
     * @param aliasName The name of the alias to be deleted.
     * @throws NullPointerException If the input is null.
     */
    public void deleteAlias(String aliasName) {
        requireNonNull(aliasName);
        this.aliasMapping.deleteAlias(aliasName);
    }

    /**
     * Returns an Alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return The alias with the specified name.
     * @throws NullPointerException If the input is null.
     */
    public Alias getAlias(String aliasName) {
        requireNonNull(aliasName);
        return this.aliasMapping.getAlias(aliasName);
    }

    /**
     * Checks if the current mapping contains an alias based on name.
     *
     * @param aliasName Name of the alias.
     * @return Whether the mapping contains the alias.
     */
    public boolean containsAlias(String aliasName) {
        return this.aliasMapping.containsAlias(aliasName);
    }

    /**
     * Checks if the alias name is a reserved keyword.
     *
     * @param aliasName Name of the alias.
     * @return Whether the name is reserved.
     * @throws NullPointerException If the input is null.
     */
    public boolean isReservedKeyword(String aliasName) {
        requireNonNull(aliasName);
        return this.aliasMapping.isReservedKeyword(aliasName);
    }

    /**
     * Checks if the command word is recursive.
     *
     * @param commandWord The command word.
     * @return Whether the command word is recursive.
     * @throws NullPointerException If the input is null.
     */
    public boolean isRecursiveKeyword(String commandWord) {
        requireNonNull(commandWord);
        return this.aliasMapping.isRecursiveKeyword(commandWord);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                        && residents.equals(((AddressBook) other).residents)
                        && rooms.equals(((AddressBook) other).rooms)
                        && issues.equals(((AddressBook) other).issues)
                        && aliasMapping.equals(((AddressBook) other).aliasMapping));
    }

    @Override
    public int hashCode() {
        return Objects.hash(residents, rooms, residentRooms, issues, aliasMapping);
    }
}
