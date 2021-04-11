package seedu.address.storage.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.room.RoomCommandTestUtil.VALID_ROOM_TYPES;
import static seedu.address.storage.room.JsonAdaptedRoom.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.room.TypicalRooms.ROOM_CORRIDOR_NON_AC_OCCUPIED;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.RoomType;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedTag;

public class JsonAdaptedRoomTest {
    private static final String INVALID_ROOM_NUMBER = "00-1231";
    private static final String INVALID_ROOM_TYPE = "Corridor_Suite";
    private static final String INVALID_OCCUPANCY = "X";
    private static final String INVALID_TAG = "#HASH-t@g";

    private static final String VALID_ROOM_NUMBER = ROOM_CORRIDOR_NON_AC_OCCUPIED.getRoomNumber().toString();
    private static final String VALID_ROOM_TYPE = VALID_ROOM_TYPES.get(0);
    private static final String VALID_OCCUPANCY = ROOM_CORRIDOR_NON_AC_OCCUPIED.isOccupied().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ROOM_CORRIDOR_NON_AC_OCCUPIED.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validRoomDetails_returnsRoom() throws Exception {
        JsonAdaptedRoom room = new JsonAdaptedRoom(ROOM_CORRIDOR_NON_AC_OCCUPIED);
        assertEquals(ROOM_CORRIDOR_NON_AC_OCCUPIED, room.toModelType());
    }

    // No more than 1 invalid value per negative case
    @Test
    public void toModelType_nullRoomNumber_throwsIllegalValueException() {
        JsonAdaptedRoom room = new JsonAdaptedRoom(null, VALID_ROOM_TYPE,
                VALID_OCCUPANCY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_invalidRoomNumber_throwsIllegalValueException() {
        JsonAdaptedRoom room = new JsonAdaptedRoom(INVALID_ROOM_NUMBER, VALID_ROOM_TYPE,
                VALID_OCCUPANCY, VALID_TAGS);
        String expectedMessage = String.format(RoomNumber.MESSAGE_CONSTRAINTS, RoomNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_nullRoomType_throwsIllegalValueException() {
        JsonAdaptedRoom room = new JsonAdaptedRoom(VALID_ROOM_NUMBER, null,
                VALID_OCCUPANCY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_invalidRoomType_throwsIllegalValueException() {
        JsonAdaptedRoom room = new JsonAdaptedRoom(VALID_ROOM_NUMBER, INVALID_ROOM_TYPE,
                VALID_OCCUPANCY, VALID_TAGS);
        String expectedMessage = String.format(RoomType.MESSAGE_CONSTRAINTS, RoomType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_nullOccupancy_throwsIllegalValueException() {
        JsonAdaptedRoom room = new JsonAdaptedRoom(VALID_ROOM_NUMBER, VALID_ROOM_TYPE,
                null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IsOccupied.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_invalidOccupancy_throwsIllegalValueException() {
        JsonAdaptedRoom room = new JsonAdaptedRoom(VALID_ROOM_NUMBER, VALID_ROOM_TYPE,
                INVALID_OCCUPANCY, VALID_TAGS);
        String expectedMessage = String.format(IsOccupied.MESSAGE_CONSTRAINTS, IsOccupied.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRoom room = new JsonAdaptedRoom(VALID_ROOM_NUMBER, VALID_ROOM_TYPE,
                INVALID_OCCUPANCY, invalidTags);
        String expectedMessage = String.format(Tag.MESSAGE_CONSTRAINTS, Tag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, room::toModelType);
    }
}
