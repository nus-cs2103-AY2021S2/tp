package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.room.TypicalRooms.ROOM_CORRIDOR_NON_AC_OCCUPIED;
import static seedu.address.testutil.room.TypicalRooms.ROOM_SUITE_AC_OCCUPIED;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.model.room.exceptions.RoomNotFoundException;
import seedu.address.testutil.room.RoomBuilder;

/**
 * Contains unit and integration (with the {@code Room}) tests for {@code UniqueRoomList}.
 */
public class UniqueRoomListTest {

    private final UniqueRoomList uniqueRoomList = new UniqueRoomList();

    @Test
    public void contains_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.contains((Room) null));
    }

    @Test
    public void contains_roomNotInList_returnsFalse() {
        assertFalse(uniqueRoomList.contains(ROOM_CORRIDOR_NON_AC_OCCUPIED));
    }

    @Test
    public void contains_roomInList_returnsTrue() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        assertTrue(uniqueRoomList.contains(ROOM_CORRIDOR_NON_AC_OCCUPIED));
    }

    @Test
    public void contains_roomWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        Room editedRoom = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED).build();
        assertTrue(uniqueRoomList.contains(editedRoom));
    }

    @Test
    public void add_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.add(null));
    }

    @Test
    public void add_duplicateRoom_throwsDuplicateRoomException() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED));
    }

    @Test
    public void setRoom_nullTargetRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueRoomList.setRoom(null, ROOM_CORRIDOR_NON_AC_OCCUPIED));
    }

    @Test
    public void setRoom_nullEditedRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueRoomList.setRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED, null));
    }

    @Test
    public void setRoom_targetRoomNotInList_throwsRoomNotFoundException() {
        assertThrows(RoomNotFoundException.class, () ->
                uniqueRoomList.setRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED, ROOM_CORRIDOR_NON_AC_OCCUPIED));
    }

    @Test
    public void setRoom_editedRoomIsSameRoom_success() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        uniqueRoomList.setRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED, ROOM_CORRIDOR_NON_AC_OCCUPIED);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRoom_editedRoomHasSameIdentity_success() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        Room editedRoom = new RoomBuilder(ROOM_CORRIDOR_NON_AC_OCCUPIED)
                .build();
        uniqueRoomList.setRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED, editedRoom);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(editedRoom);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRoom_editedRoomHasDifferentIdentity_success() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        uniqueRoomList.setRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED, ROOM_SUITE_AC_OCCUPIED);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_SUITE_AC_OCCUPIED);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRoom_editedRoomHasNonUniqueIdentity_throwsDuplicateRoomException() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        uniqueRoomList.add(ROOM_SUITE_AC_OCCUPIED);
        assertThrows(DuplicateRoomException.class, () ->
                uniqueRoomList.setRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED, ROOM_SUITE_AC_OCCUPIED));
    }

    @Test
    public void remove_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.remove(null));
    }

    @Test
    public void remove_roomDoesNotExist_throwsRoomNotFoundException() {
        assertThrows(RoomNotFoundException.class, () -> uniqueRoomList.remove(ROOM_CORRIDOR_NON_AC_OCCUPIED));
    }

    @Test
    public void remove_existingRoom_removesRoom() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        uniqueRoomList.remove(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRooms_nullUniqueRoomList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRooms((UniqueRoomList) null));
    }

    @Test
    public void setRooms_uniqueRoomList_replacesOwnListWithProvidedUniqueRoomList() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_SUITE_AC_OCCUPIED);
        uniqueRoomList.setRooms(expectedUniqueRoomList);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRooms_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.setRooms((List<Room>) null));
    }

    @Test
    public void setRooms_list_replacesOwnListWithProvidedList() {
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        List<Room> roomList = Collections.singletonList(ROOM_SUITE_AC_OCCUPIED);
        uniqueRoomList.setRooms(roomList);
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();
        expectedUniqueRoomList.add(ROOM_SUITE_AC_OCCUPIED);
        assertEquals(expectedUniqueRoomList, uniqueRoomList);
    }

    @Test
    public void setRooms_listWithDuplicateRooms_throwsDuplicateRoomException() {
        List<Room> listWithDuplicateRooms = Arrays.asList(ROOM_CORRIDOR_NON_AC_OCCUPIED, ROOM_CORRIDOR_NON_AC_OCCUPIED);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList
                .setRooms(listWithDuplicateRooms));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueRoomList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        UniqueRoomList expectedUniqueRoomList = new UniqueRoomList();

        // EP: same object -> return true (when empty)
        assertEquals(uniqueRoomList, uniqueRoomList);

        // EP: diff types -> return false
        assertNotEquals(uniqueRoomList, 5);

        // EP: null -> return false
        assertNotEquals(uniqueRoomList, null);

        // EP: diff object -> return true (when empty)
        assertEquals(uniqueRoomList, expectedUniqueRoomList);


        // EP: same object -> return true (with items)
        uniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        assertEquals(uniqueRoomList, uniqueRoomList);

        // EP: diff object -> return true (with same items)
        expectedUniqueRoomList.add(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        assertEquals(uniqueRoomList, expectedUniqueRoomList);

        // EP: diff object, diff items -> return false (with same items)
        expectedUniqueRoomList.add(ROOM_SUITE_AC_OCCUPIED);
        assertNotEquals(uniqueRoomList, expectedUniqueRoomList);

    }
}
