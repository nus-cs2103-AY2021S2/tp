package seedu.address.model.residentroom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.ALICE;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.residentroom.exceptions.DuplicateResidentRoomException;
import seedu.address.model.residentroom.exceptions.ResidentRoomNotFoundException;
import seedu.address.testutil.residentroom.ResidentRoomBuilder;

public class UniqueResidentRoomListTest {

    private final UniqueResidentRoomList uniqueResidentRoomList = new UniqueResidentRoomList();
    private final ResidentRoom bobEdited = new ResidentRoomBuilder(BOB)
            .withRoomNumber(ALICE.getRoomNumber().toString()).build();
    private final ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE)
                .withRoomNumber(BOB.getRoomNumber().toString()).build();

    // ----------------------  Tests for containsEitherResidentRoom  ----------------------
    @Test
    public void containsEitherResidentRoom_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueResidentRoomList.containsEitherResidentRoom((ResidentRoom) null));
    }

    @Test
    public void containsEitherResidentRoom_residentRoomNotInList_returnsFalse() {
        assertFalse(uniqueResidentRoomList.containsEitherResidentRoom(ALICE));
    }

    @Test
    public void containsEitherResidentRoom_exactResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE);
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(ALICE));
    }

    @Test
    public void containsEitherResidentRoom_residentRoomWithSameIdentityFieldsInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE);
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE).build();
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(editedAlice));
    }

    @Test
    public void containsEitherResidentRoom_sameResidentDifferentRoomInList_returnsTrue() {

        uniqueResidentRoomList.add(editedAlice);
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(ALICE));
    }

    @Test
    public void containsEitherResidentRoom_differentResidentSameRoomInList_returnsTrue() {
        ResidentRoom bobEdited = new ResidentRoomBuilder(BOB)
                .withRoomNumber(ALICE.getRoomNumber().toString())
                .build();
        uniqueResidentRoomList.add(bobEdited);
        assertTrue(uniqueResidentRoomList.containsEitherResidentRoom(ALICE));
    }

    @Test
    public void containsEitherResidentRoom_differentResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(BOB);
        assertFalse(uniqueResidentRoomList.containsEitherResidentRoom(ALICE));
    }

    // ----------------------  Tests for containsBothResidentRoom  ----------------------
    @Test
    public void containsBothResidentRoom_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueResidentRoomList.containsBothResidentRoom((ResidentRoom) null));
    }

    @Test
    public void containsBothResidentRoom_residentRoomNotInList_returnsFalse() {
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE));
    }

    @Test
    public void containsBothResidentRoom_exactResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE);
        assertTrue(uniqueResidentRoomList.containsBothResidentRoom(ALICE));
    }

    @Test
    public void containsBothResidentRoom_residentRoomWithSameIdentityFieldsInList_returnsTrue() {
        uniqueResidentRoomList.add(ALICE);
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE).build();
        assertTrue(uniqueResidentRoomList.containsBothResidentRoom(editedAlice));
    }

    @Test
    public void containsBothResidentRoom_sameResidentDifferentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(editedAlice);
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE));
    }

    @Test
    public void containsBothResidentRoom_differentResidentSameRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(bobEdited);
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE));
    }

    @Test
    public void containsBothResidentRoom_differentResidentRoomInList_returnsTrue() {
        uniqueResidentRoomList.add(BOB);
        assertFalse(uniqueResidentRoomList.containsBothResidentRoom(ALICE));
    }

    @Test
    public void add_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentRoomList.add(null));
    }

    @Test
    public void add_duplicateResidentRoom_throwsDuplicateResidentRoomException() {
        uniqueResidentRoomList.add(ALICE);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList.add(ALICE));
    }

    @Test
    public void add_sameResidentDifferentRoom_throwsDuplicateResidentRoomException() {
        uniqueResidentRoomList.add(editedAlice);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList.add(ALICE));
    }

    @Test
    public void add_differentResidentSameRoom_throwsDuplicateResidentRoomException() {
        uniqueResidentRoomList.add(bobEdited);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList.add(ALICE));
    }

    @Test
    public void remove_nullResidentRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueResidentRoomList.remove(null));
    }

    @Test
    public void remove_residentDoesNotExist_throwsResidentRoomNotFoundException() {
        assertThrows(ResidentRoomNotFoundException.class, () -> uniqueResidentRoomList.remove(ALICE));
    }

    @Test
    public void remove_existingResidentRoom_removesResidentRoom() {
        uniqueResidentRoomList.add(ALICE);
        uniqueResidentRoomList.remove(ALICE);
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
        uniqueResidentRoomList.add(ALICE);
        List<ResidentRoom> residentRoomList = Collections.singletonList(BOB);
        uniqueResidentRoomList.setResidentRooms(residentRoomList);
        UniqueResidentRoomList expectedUniqueResidentRoomList = new UniqueResidentRoomList();
        expectedUniqueResidentRoomList.add(BOB);
        assertEquals(expectedUniqueResidentRoomList, uniqueResidentRoomList);
    }

    @Test
    public void setResidentRooms_listWithDuplicateResidentRoom_throwsDuplicateResidentRoomException() {
        List<ResidentRoom> listWithDuplicateResidentRoom = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateResidentRoomException.class, () -> uniqueResidentRoomList
                .setResidentRooms(listWithDuplicateResidentRoom));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueResidentRoomList.asUnmodifiableObservableList().remove(0));
    }
}
