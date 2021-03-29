package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.RoomNumber;

/**
 * Jackson-friendly version of {@link Resident}.
 */
class JsonAdaptedResidentRoom {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Resident's %s field is missing!";

    private final String name;
    private final String roomNumber;

    /**
     * Constructs a {@code JsonAdaptedResident} with the given resident details.
     */
    @JsonCreator
    public JsonAdaptedResidentRoom(@JsonProperty("name") String name, @JsonProperty("roomNumber") String roomNumber) {
        this.name = name;
        this.roomNumber = roomNumber;
    }

    /**
     * Converts a given {@code Resident} into this class for Jackson use.
     */
    public JsonAdaptedResidentRoom(ResidentRoom source) {
        name = source.getName().fullName;
        roomNumber = source.getRoomNumber().toString();
    }

    /**
     * Converts this Jackson-friendly adapted resident object into the model's {@code Resident} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted resident.
     */
    public ResidentRoom toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (roomNumber == null) {
            throw new IllegalValueException(String
                    .format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName()));
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        return new ResidentRoom(modelName, modelRoomNumber);
    }

}
