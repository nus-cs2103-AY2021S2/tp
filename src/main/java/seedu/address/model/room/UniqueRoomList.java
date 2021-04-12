package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.model.room.exceptions.RoomNotFoundException;

/**
 * A list of rooms that enforces uniqueness between its elements and does not allow nulls.
 * A room is considered unique by comparing using {@code Room#isSameRoom(Room)}. As such, adding and updating of
 * rooms uses Room#isSameRoom(Room) for equality so as to ensure that the room being added or updated is
 * unique in terms of identity in the UniqueRoomList. However, the removal of a person uses Room#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Room#isSameRoom(Room)
 */
public class UniqueRoomList implements Iterable<Room> {

    private final ObservableList<Room> internalList = FXCollections.observableArrayList();
    private final ObservableList<Room> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks the list contains an equivalent {@code Room} as the given argument.
     *
     * @param toCheck {@code Room} to check if it is contained within the {@link UniqueRoomList#internalList}
     * @return True if the list contains an equivalent {@code Room} as the given argument.
     * @throws NullPointerException If {@code toCheck} is null.
     */
    public boolean contains(Room toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRoom);
    }

    /**
     * Checks the list contains a {@code Room} which has the {@code RoomNumber} of the given argument.
     *
     * @param toCheck {@code RoomNumber} to check if there is a room contained within the
     *                {@link UniqueRoomList#internalList} that matches it.
     * @return True if the list contains an a {@code Room} with the {@code RoomNumber} as the given argument.
     * @throws NullPointerException If {@code toCheck} is null.
     */
    public boolean contains(RoomNumber toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRoom);
    }

    /**
     * Adds a room to the list.
     *
     * @param toAdd {@code Room} to add.
     * @throws NullPointerException If {@code toAdd} is null.
     */
    public void add(Room toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRoomException();
        }
        internalList.add(toAdd);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the list.
     * The Room identity of {@code editedRoom} must not be the same as another existing room in the list.
     *
     * @param target     {@code Room} to replace.
     * @param editedRoom {@code Room} to replace {@code target} with.
     * @throws NullPointerException If {@code target} or {@code editedRoom} are null.
     */
    public void setRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RoomNotFoundException();
        }

        if (!target.isSameRoom(editedRoom) && contains(editedRoom)) {
            throw new DuplicateRoomException();
        }

        internalList.set(index, editedRoom);
        FXCollections.sort(internalList);
    }

    /**
     * Removes the equivalent room from the list.
     * The room must exist in the list.
     *
     * @param toRemove {@code Room} to remove.
     * @throws NullPointerException If {@code toRemove} is null.
     */
    public void remove(Room toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RoomNotFoundException();
        }
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the entire {@code UniqueRoomList} with the provided {@code replacement}
     *
     * @param replacement {@code UniqueRoomList} to replace the existing one with.
     * @throws NullPointerException If {@code replacement} is null.
     */
    public void setRooms(UniqueRoomList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        FXCollections.sort(internalList);
    }

    /**
     * Replaces the contents of this list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     *
     * @param rooms List of rooms to replace the existing List with.
     * @throws NullPointerException If {@code rooms} is null.
     */
    public void setRooms(List<Room> rooms) {
        requireAllNonNull(rooms);
        if (!roomsAreUnique(rooms)) {
            throw new DuplicateRoomException();
        }

        internalList.setAll(rooms);
        FXCollections.sort(internalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return {@code ObservableList<Room>} contained within this {@code UniqueRoomList}.
     */
    public ObservableList<Room> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Room> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRoomList // instanceof handles nulls
                && internalList.equals(((UniqueRoomList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code rooms} contains only unique rooms.
     */
    private boolean roomsAreUnique(List<Room> rooms) {
        for (int i = 0; i < rooms.size() - 1; i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).isSameRoom(rooms.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
