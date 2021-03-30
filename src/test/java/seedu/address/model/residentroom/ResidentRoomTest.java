package seedu.address.model.residentroom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BOB;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.ALICE;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.residentroom.ResidentRoomBuilder;

public class ResidentRoomTest {

    @Test
    public void isSameResident() {
        // same object -> returns true
        assertTrue(ALICE.isSameResidentRoom(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameResidentRoom(null));

        // same name, different room number -> returns true
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_BOB)
                .build();
        assertTrue(ALICE.isSameResidentRoom(editedAlice));

        // different name, same room number -> returns true
        ResidentRoom editedBob = new ResidentRoomBuilder(ALICE)
                .withName(VALID_NAME_BOB)
                .build();
        assertTrue(ALICE.isSameResidentRoom(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        ResidentRoom aliceCopy = new ResidentRoomBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different name, different room -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name, same room -> returns false
        ResidentRoom editedAlice = new ResidentRoomBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // same name, different room -> returns false
        editedAlice = new ResidentRoomBuilder(ALICE).withRoomNumber(VALID_ROOM_NUMBER_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
