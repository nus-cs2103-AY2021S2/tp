package seedu.address.model.residentroom;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.exceptions.DuplicateResidentException;
import seedu.address.model.resident.exceptions.ResidentNotFoundException;
import seedu.address.model.residentroom.exceptions.DuplicateResidentRoomException;
import seedu.address.model.residentroom.exceptions.ResidentRoomNotFoundException;

/**
 * A list of residents that enforces uniqueness between its elements and does not allow nulls.
 * A resident is considered unique by comparing using {@code Resident#isSameResident(Resident)}.
 * As such, adding and updating of resident uses Resident#isSameResident(Resident) for equality so as to
 * ensure that the resident being added or updated is unique in terms of identity in the UniqueResidentList.
 * However, the removal of a resident uses Resident#equals(Object) so
 * as to ensure that the resident with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Resident#isSameResident(Resident)
 */
public class UniqueResidentRoomList implements Iterable<ResidentRoom> {

    private final ObservableList<ResidentRoom> internalList = FXCollections.observableArrayList();
    private final ObservableList<ResidentRoom> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains the name or room number as the given argument.
     */
    public boolean containsEitherResidentRoom(ResidentRoom toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameResidentRoom);
    }

    /**
     * Returns true if the list matches the resident and room number as the given argument.
     */
    public boolean containsBothResidentRoom(ResidentRoom toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a residentroom to the list.
     * The residentroom must not already exist in the list.
     */
    public void add(ResidentRoom toAdd) {
        requireNonNull(toAdd);
        if (containsEitherResidentRoom(toAdd)) {
            throw new DuplicateResidentRoomException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the residentroom {@code target} in the list with {@code editedResidentRoom}.
     * {@code target} must exist in the list.
     * The residentroom identity of {@code editedResidentRoom} must not be the same
     * as another existing residentRoom in the list.
     */
    public void setResidentRoom(ResidentRoom target, ResidentRoom editedResidentRoom) {
        requireAllNonNull(target, editedResidentRoom);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ResidentNotFoundException();
        }

        if (!target.isSameResidentRoom(editedResidentRoom) && containsEitherResidentRoom(editedResidentRoom)) {
            throw new DuplicateResidentException();
        }

        internalList.set(index, editedResidentRoom);
    }

    /**
     * Removes the equivalent resident from the list.
     * The resident must exist in the list.
     */
    public void remove(ResidentRoom toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ResidentRoomNotFoundException();
        }
    }

    public void setResidentRooms(UniqueResidentRoomList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code residentRoom}.
     * {@code residentRooms} must not contain duplicate residentRooms.
     */
    public void setResidentRooms(List<ResidentRoom> residentRooms) {
        requireAllNonNull(residentRooms);
        if (!residentRoomsAreUnique(residentRooms)) {
            throw new DuplicateResidentRoomException();
        }
        internalList.setAll(residentRooms);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ResidentRoom> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ResidentRoom> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueResidentRoomList // instanceof handles nulls
                        && internalList.equals(((UniqueResidentRoomList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code residents} contains only unique residents.
     */
    private boolean residentRoomsAreUnique(List<ResidentRoom> residentRooms) {
        for (int i = 0; i < residentRooms.size() - 1; i++) {
            for (int j = i + 1; j < residentRooms.size(); j++) {
                if (residentRooms.get(i).isSameResidentRoom(residentRooms.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
