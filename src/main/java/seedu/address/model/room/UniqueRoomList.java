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
     * Returns true if the list contains an equivalent room as the given argument.
     */
    public boolean contains(Room toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRoom);
    }

    /**
     * Returns true if the list contains an equivalent room as the given argument.
     */
    public boolean contains(RoomNumber toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRoom);
    }

    /**
     * Adds a room to the list.
     * The room must not already exist in the list.
     */
    public void add(Room toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRoomException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the list.
     * The Room identity of {@code editedRoom} must not be the same as another existing room in the list.
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
    }

    /**
     * Removes the equivalent room from the list.
     * The room must exist in the list.
     */
    public void remove(Room toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RoomNotFoundException();
        }
    }

    public void setRooms(UniqueRoomList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     */
    public void setRooms(List<Room> rooms) {
        requireAllNonNull(rooms);
        if (!roomsAreUnique(rooms)) {
            throw new DuplicateRoomException();
        }

        internalList.setAll(rooms);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
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
