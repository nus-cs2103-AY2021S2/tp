package seedu.address.testutil.residentroom;

import seedu.address.model.resident.Name;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.RoomNumber;

/**
 * A utility class to help with building Resident objects.
 */
public class ResidentRoomBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ROOM_NUMBER = "01-123";

    private Name name;
    private RoomNumber roomNumber;

    /**
     * Creates a {@code ResidentRoomBuilder} with the default details.
     */
    public ResidentRoomBuilder() {
        name = new Name(DEFAULT_NAME);
        roomNumber = new RoomNumber(DEFAULT_ROOM_NUMBER);
    }

    /**
     * Initializes the ResidentRoomBuilder with the data of {@code residentRoomToCopy}.
     */
    public ResidentRoomBuilder(ResidentRoom residentRoomToCopy) {
        name = residentRoomToCopy.getName();
        roomNumber = residentRoomToCopy.getRoomNumber();
    }

    /**
     * Sets the {@code Name} of the {@code ResidentRoom} that we are building.
     */
    public ResidentRoomBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code ResidentRoom} that we are building.
     */
    public ResidentRoomBuilder withRoomNumber(String room) {
        this.roomNumber = new RoomNumber(room);
        return this;
    }

    public ResidentRoom build() {
        return new ResidentRoom(name, roomNumber);
    }
}
