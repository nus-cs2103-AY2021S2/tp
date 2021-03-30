package seedu.address.testutil.room;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.RoomType;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Room objects.
 */
public class RoomBuilder {
    public static final String DEFAULT_ROOM_NUMBER = "01-001";
    public static final String DEFAULT_ROOM_TYPE = "CORRIDOR_NON_AC";
    public static final String DEFAULT_OCCUPANCY_STATUS = "N";

    private RoomNumber roomNumber;
    private RoomType type;
    private IsOccupied isOccupied;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Creates a {@code RoomBuilder} with the default details.
     */
    public RoomBuilder() {
        this.roomNumber = new RoomNumber(DEFAULT_ROOM_NUMBER);
        this.type = new RoomType(DEFAULT_ROOM_TYPE);
        this.isOccupied = new IsOccupied(DEFAULT_OCCUPANCY_STATUS);
        this.tags = new HashSet<>();
    }

    /**
     * Initializes the RoomBuilder with the data of {@code roomToCopy}.
     */
    public RoomBuilder(Room roomToCopy) {
        this.roomNumber = roomToCopy.getRoomNumber();
        this.type = roomToCopy.getRoomType();
        this.isOccupied = roomToCopy.isOccupied();
        this.tags = new HashSet<>(roomToCopy.getTags());
    }

    /**
     * Sets the {@code RoomNumber} of the {@code Room} that we are building.
     */
    public RoomBuilder withRoomNumber(String roomNumber) {
        this.roomNumber = new RoomNumber(roomNumber);
        return this;
    }

    /**
     * Sets the {@code RoomType} of the {@code Room} that we are building.
     */
    public RoomBuilder withRoomType(String roomType) {
        this.type = new RoomType(roomType);
        return this;
    }

    /**
     * Sets the {@code IsOccupied} of the {@code Room} that we are building.
     */
    public RoomBuilder withOccupancyStatus(String isOccupied) {
        this.isOccupied = new IsOccupied(isOccupied);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Room} that we are building.
     */
    public RoomBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Builds the room based on the provided parameters
     *
     * @return Room with properties issued through the various methods of the {@code RoomBuilder} class
     */
    public Room build() {
        return new Room(roomNumber, type, isOccupied, tags);
    }

}
