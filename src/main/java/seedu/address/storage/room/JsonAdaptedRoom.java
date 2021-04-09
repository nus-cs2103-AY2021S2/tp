package seedu.address.storage.room;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.RoomType;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedTag;


/**
 * Jackson-friendly version of {@link Room}.
 */
public class JsonAdaptedRoom {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Room's %s field is missing!";

    private final String roomNumber;
    private final String roomType;
    private final String isOccupied;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRoom} with the given room details.
     */
    @JsonCreator
    public JsonAdaptedRoom(@JsonProperty("roomNumber") String roomNumber, @JsonProperty("roomType") String roomType,
                           @JsonProperty("isOccupied") String isOccupied,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isOccupied = isOccupied;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }
    /**
     * Converts a given {@code Room} into this class for Jackson use.
     */
    public JsonAdaptedRoom(Room source) {
        this.roomNumber = source.getRoomNumber().roomNumber;
        this.roomType = source.getRoomType().value.toString();
        this.isOccupied = source.isOccupied().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted room object into the model's {@code Room} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted room.
     */
    public Room toModelType() throws IllegalValueException {
        final List<Tag> roomTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            roomTags.add(tag.toModelType());
        }

        if (roomNumber == null) {
            throw new IllegalValueException(String
                    .format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName()));
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        if (roomType == null) {
            throw new IllegalValueException(String
                    .format(MISSING_FIELD_MESSAGE_FORMAT, RoomType.class.getSimpleName()));
        }
        if (!RoomType.isValidRoomType(roomType)) {
            throw new IllegalValueException(RoomType.MESSAGE_CONSTRAINTS);
        }
        final RoomType modelRoomType = new RoomType(roomType);

        if (isOccupied == null) {
            throw new IllegalValueException(String
                    .format(MISSING_FIELD_MESSAGE_FORMAT, IsOccupied.class.getSimpleName()));
        }
        if (!IsOccupied.isValidOccupancyStatus(isOccupied)) {
            throw new IllegalValueException(IsOccupied.MESSAGE_CONSTRAINTS);
        }
        final IsOccupied modelRoomOccupancyStatus = new IsOccupied(isOccupied);

        final Set<Tag> modelRoomTags = new HashSet<>(roomTags);
        return new Room(modelRoomNumber, modelRoomType, modelRoomOccupancyStatus, modelRoomTags);
    }
}
