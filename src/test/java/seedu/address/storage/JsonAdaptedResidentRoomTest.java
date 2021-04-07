package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BOB;
import static seedu.address.storage.JsonAdaptedResident.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.resident.Name;
import seedu.address.model.room.RoomNumber;
import seedu.address.testutil.resident.TypicalResidents;
import seedu.address.testutil.residentroom.TypicalResidentRooms;

public class JsonAdaptedResidentRoomTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ROOM_NUMBER = "03145";

    private static final String VALID_NAME = TypicalResidents.BOB.getName().toString();
    private static final String VALID_ROOM_NUMBER = TypicalResidents.BOB.getRoom().toString();

    @Test
    public void toModelType_validResidentRoom_returnsResidentRoom() throws Exception {
        JsonAdaptedResidentRoom residentRoom = new JsonAdaptedResidentRoom(TypicalResidentRooms.BOB_ROOM_NUMBER);
        assertEquals(TypicalResidentRooms.BOB_ROOM_NUMBER, residentRoom.toModelType());
    }

    @Test
    public void toModelType_validResidentRoomDetails_returnsResidentRoom() throws Exception {
        JsonAdaptedResidentRoom residentRoom = new JsonAdaptedResidentRoom(VALID_NAME_BOB, VALID_ROOM_NUMBER_BOB);
        assertEquals(TypicalResidentRooms.BOB_ROOM_NUMBER, residentRoom.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedResidentRoom residentRoom =
                new JsonAdaptedResidentRoom(INVALID_NAME, VALID_ROOM_NUMBER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, residentRoom::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedResidentRoom residentRoom = new JsonAdaptedResidentRoom(null, VALID_ROOM_NUMBER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, residentRoom::toModelType);
    }

    @Test
    public void toModelType_invalidRoomNumber_throwsIllegalValueException() {
        JsonAdaptedResidentRoom residentRoom =
                new JsonAdaptedResidentRoom(VALID_NAME, INVALID_ROOM_NUMBER);
        String expectedMessage = RoomNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, residentRoom::toModelType);
    }

    @Test
    public void toModelType_nullRoomNumber_throwsIllegalValueException() {
        JsonAdaptedResidentRoom residentRoom =
                new JsonAdaptedResidentRoom(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, residentRoom::toModelType);
    }
}
