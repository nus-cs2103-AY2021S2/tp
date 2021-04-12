package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Room in SunRez.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Room implements Comparable<Room> {
    // Rooms have a default occupancy status of "No". Occupancy
    // can only be edited through the allocation/deallocation commands
    // or through the reading of the model from a JSON file (which isn't
    // really editing, its just model creation)
    public static final String DEFAULT_OCCUPANCY_STATUS = "N";
    // Identity fields
    // Room number, type, occupancy status, tags
    private final RoomNumber roomNumber;
    private final RoomType roomType;
    private final IsOccupied isOccupied;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a Room. This constructor should only be used when creating a room model from the JSON file.
     * Commands such as {@code AddRoomCommand} or {@code EditRoomCommand} and their respective parsers should
     * NOT use this constructor. Use the one that does not take in {@code IsOccupied}.
     *
     * @param roomNumber The {@code RoomNumber} of the {@code Room} to create.
     * @param roomType   The {@code RoomType} of the {@code Room} to create.
     * @param isOccupied The {@code IsOccupied} value of the {@code Room} to create.
     * @param tags       {@code Set<Tag>} containing the {@code Tag}s of the {@code Room} to create.
     * @throws NullPointerException If any of the provided parameters are null.
     */
    public Room(RoomNumber roomNumber, RoomType roomType, IsOccupied isOccupied, Set<Tag> tags) {
        requireAllNonNull(roomNumber, roomType, isOccupied, tags);
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isOccupied = isOccupied;
        this.tags.addAll(tags);
    }

    /**
     * Constructs a Room with the default {@code IsOccupied} value of "No".
     * This constructor should be used in most cases, i.e creating a room using the {@code AddRoomCommand} or
     * {@code EditRoomCommand}.
     *
     * @param roomNumber The {@code RoomNumber} of the {@code Room} to create.
     * @param roomType   The {@code RoomType} of the {@code Room} to create.
     * @param tags       {@code Set<Tag>} containing the {@code Tag}s of the {@code Room} to create.
     * @throws NullPointerException If any of the provided parameters are null.
     */
    public Room(RoomNumber roomNumber, RoomType roomType, Set<Tag> tags) {
        requireAllNonNull(roomNumber, roomType, tags);
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isOccupied = new IsOccupied(DEFAULT_OCCUPANCY_STATUS);
        this.tags.addAll(tags);
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public IsOccupied isOccupied() {
        return isOccupied;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both rooms have the same number.
     * This defines a weaker notion of equality between two rooms.
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
                && otherRoom.getRoomNumber().equals(getRoomNumber());
    }


    /**
     * Returns true if both rooms have the same identity and data fields.
     * This defines a stronger notion of equality between two rooms.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Room)) {
            return false;
        }

        Room otherRoom = (Room) other;
        return otherRoom.getRoomNumber().equals(getRoomNumber())
                && otherRoom.getRoomType().equals(getRoomType())
                && otherRoom.isOccupied().equals(isOccupied())
                && otherRoom.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(roomNumber, roomType, isOccupied, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getRoomNumber())
                .append("; Type: ")
                .append(getRoomType())
                .append("; Occupancy Status: ")
                .append(isOccupied());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Room room) {
        return this.roomNumber.compareTo(room.getRoomNumber());
    }
}
