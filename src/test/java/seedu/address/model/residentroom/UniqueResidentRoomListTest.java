package seedu.address.model.residentroom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.ALICE_ROOM_NUMBER;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.BOB_ROOM_NUMBER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.residentroom.exceptions.DuplicateResidentRoomException;
import seedu.address.model.residentroom.exceptions.ResidentRoomNotFoundException;
import seedu.address.testutil.residentroom.ResidentRoomBuilder;

public class UniqueResidentRoomListTest {

    private final UniqueResidentRoomList uniqueResidentRoomList = new UniqueResidentRoomList();
    private final ResidentRoom bobEdited = new ResidentRoomBuilder(BOB_ROOM_NUMBER)
            .withRoomNumber(ALICE_ROOM_NUMBER.getRoomNumber().toString()).build();
    private final ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE_ROOM_NUMBER)
                .withRoomNumber(BOB_ROOM_NUMBER.getRoomNumber().toString()).build();

    // ----------------------  Tests for containsEitherResidentRoom  ----------------------
    @Test
    public void containsEitherResidentRoom_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueResidentRoomList.containsEitherResidentRoom((ResidentRoom) null));
    }

    @Test
    public void containsEitherResidentRoom_residentRoomNotInList_returnsFalse() {
        assertFalse(uniqueResidentRoomList.containsEitherResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsEitherResidentRoom_exactResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE_ROOM_NUMBER);
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsEitherResidentRoom_residentRoomWithSameIdentityFieldsInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE_ROOM_NUMBER);
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE_ROOM_NUMBER).build();
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(editedAlice));
    }

    @Test
    public void containsEitherResidentRoom_sameResidentDifferentRoomInList_returnsTrue() {

        uniqueResidentRoomList.add(editedAlice);
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsEitherResidentRoom_differentResidentSameRoomInList_returnsTrue() {
        ResidentRoom bobEdited = new ResidentRoomBuilder(BOB_ROOM_NUMBER)
                .withRoomNumber(ALICE_ROOM_NUMBER.getRoomNumber().toString())
                .build();
        uniqueResidentRoomList.add(bobEdited);
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsEitherResidentRoom_differentResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(BOB_ROOM_NUMBER);
        assertFalse(uniqueResidentRoomList.containsEitherResidentRoom(ALICE_ROOM_NUMBER));
    }

    // ----------------------  Tests for containsBothResidentRoom  ----------------------
    @Test
    public void containsBothResidentRoom_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueResidentRoomList.containsBothResidentRoom((ResidentRoom) null));
    }

    @Test
    public void containsBothResidentRoom_residentRoomNotInList_returnsFalse() {
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsBothResidentRoom_exactResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE_ROOM_NUMBER);
        assertTrue(uniqueResidentRoomList.containsBothResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsBothResidentRoom_residentRoomWithSameIdentityFieldsInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE_ROOM_NUMBER);
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE_ROOM_NUMBER).build();
        assertTrue(uniqueResidentRoomList.containsBothResidentRoom(editedAlice));
    }

    @Test
    public void containsBothResidentRoom_sameResidentDifferentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(editedAlice);
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsBothResidentRoom_differentResidentSameRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(bobEdited);
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void containsBothResidentRoom_differentResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(BOB_ROOM_NUMBER);
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void add_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentRoomList.add(null));
    }

    @Test
    public void add_duplicateResidentRoom_throwsDuplicateResidentRoomException() {
        uniqueResidentRoomList.add(ALICE_ROOM_NUMBER);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList.add(ALICE_ROOM_NUMBER));
    }

    @Test
    public void add_sameResidentDifferentRoom_throwsDuplicateResidentRoomException() {
        uniqueResidentRoomList.add(editedAlice);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList.add(ALICE_ROOM_NUMBER));
    }

    @Test
    public void add_differentResidentSameRoom_throwsDuplicateResidentRoomException() {
        uniqueResidentRoomList.add(bobEdited);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList.add(ALICE_ROOM_NUMBER));
    }

    @Test
    public void remove_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentRoomList.remove(null));
    }

    @Test
    public void remove_residentDoesNotExist_throwsResidentRoomNotFoundException() {
        assertThrows(ResidentRoomNotFoundException.class, () -> uniqueResidentRoomList.remove(ALICE_ROOM_NUMBER));
    }

    @Test
    public void remove_existingResidentRoom_removesResidentRoom() {
        uniqueResidentRoomList.add(ALICE_ROOM_NUMBER);
        uniqueResidentRoomList.remove(ALICE_ROOM_NUMBER);
        UniqueResidentRoomList expectedUniqueResidentRoomList = new UniqueResidentRoomList();
        assertEquals(expectedUniqueResidentRoomList, uniqueResidentRoomList);
    }


    @Test
    public void setResidentRooms_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueResidentRoomList.setResidentRooms((List<ResidentRoom>) null));
    }

    @Test
    public void setResidentRooms_list_replacesOwnListWithProvidedList() {
        uniqueResidentRoomList.add(ALICE_ROOM_NUMBER);
        List<ResidentRoom> residentRoomList = Collections.singletonList(BOB_ROOM_NUMBER);
        uniqueResidentRoomList.setResidentRooms(residentRoomList);
        UniqueResidentRoomList expectedUniqueResidentRoomList = new UniqueResidentRoomList();
        expectedUniqueResidentRoomList.add(BOB_ROOM_NUMBER);
        assertEquals(expectedUniqueResidentRoomList, uniqueResidentRoomList);
    }

    @Test
    public void setResidentRooms_listWithDuplicateResidentRoom_throwsDuplicateResidentRoomException() {
        List<ResidentRoom> listWithDuplicateResidentRoom = Arrays.asList(ALICE_ROOM_NUMBER, ALICE_ROOM_NUMBER);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList
                .setResidentRooms(listWithDuplicateResidentRoom));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueResidentRoomList.asUnmodifiableObservableList().remove(0));
    }
}
