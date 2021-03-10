package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueRoomList rooms;

    /*
     *  The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     *  between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     *  Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *  among constructors.
     */
    {
        persons = new UniquePersonList();
        rooms = new UniqueRoomList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
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

    //// meta methods

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setRooms(newData.getRoomList());
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, rooms);
    }
}
