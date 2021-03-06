package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Room in the hostel management system.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Room {
    // Identity fields
    // Room number, type, occupation status, tags
    private final RoomNumber roomNumber;
    private final RoomType type;
    private final IsOccupied isOccupied;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Room(RoomNumber roomNumber, RoomType type, IsOccupied isOccupied, Set<Tag> tags) {
        requireAllNonNull(roomNumber, type, isOccupied, tags);
        this.roomNumber = roomNumber;
        this.type = type;
        this.isOccupied = isOccupied;
        this.tags.addAll(tags);
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public IsOccupied isOccupied() {
        return isOccupied;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

}
