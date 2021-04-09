package seedu.address.model.residentroom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BOB;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.ALICE_ROOM_NUMBER;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.BOB_ROOM_NUMBER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.residentroom.ResidentRoomBuilder;

public class ResidentRoomTest {

    @Test
    public void isSameResident() {
        // same object -> returns true
        assertTrue(ALICE_ROOM_NUMBER.isSameResidentRoom(ALICE_ROOM_NUMBER));

        // null -> returns false
        assertFalse(ALICE_ROOM_NUMBER.isSameResidentRoom(null));

        // same name, different room number -> returns true
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE_ROOM_NUMBER)
                .withRoomNumber(VALID_ROOM_NUMBER_BOB)
                .build();
        assertTrue(ALICE_ROOM_NUMBER.isSameResidentRoom(editedAlice));

        // different name, same room number -> returns true
        ResidentRoom editedBob = new ResidentRoomBuilder(ALICE_ROOM_NUMBER)
                .withName(VALID_NAME_BOB)
                .build();
        assertTrue(ALICE_ROOM_NUMBER.isSameResidentRoom(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        ResidentRoom aliceCopy = new ResidentRoomBuilder(ALICE_ROOM_NUMBER).build();
        assertTrue(ALICE_ROOM_NUMBER.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_ROOM_NUMBER.equals(ALICE_ROOM_NUMBER));

        // null -> returns false
        assertFalse(ALICE_ROOM_NUMBER.equals(null));

        // different type -> returns false
        assertFalse(ALICE_ROOM_NUMBER.equals(5));

        // different name, different room -> returns false
        assertFalse(ALICE_ROOM_NUMBER.equals(BOB_ROOM_NUMBER));

        // different name, same room -> returns false
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE_ROOM_NUMBER).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_ROOM_NUMBER.equals(editedAlice));

        // same name, different room -> returns false
        editedAlice = new ResidentRoomBuilder(ALICE_ROOM_NUMBER).withRoomNumber(VALID_ROOM_NUMBER_BOB).build();
        assertFalse(ALICE_ROOM_NUMBER.equals(editedAlice));
    }
}
