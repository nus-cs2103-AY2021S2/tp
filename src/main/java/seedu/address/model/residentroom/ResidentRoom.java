package seedu.address.model.residentroom;

import java.util.Objects;

import seedu.address.model.resident.Name;
import seedu.address.model.room.RoomNumber;

/**
 * Represents a ResidentRoom.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ResidentRoom {
    // Identity fields
    private final Name name;
    private final RoomNumber roomNumber;

    /**
     * Every field must be present and not null.
     */
    public ResidentRoom(Name name, RoomNumber roomNumber) {
        this.name = name;
        this.roomNumber = roomNumber;
    }

    public Name getName() {
        return name;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns true if both residents have the same name or
     * returns true if both rooms are the same.
     * This defines a weaker notion of equality between two residentroom.
     */
    public boolean isSameResidentRoom(ResidentRoom otherResidentRoom) {
        if (otherResidentRoom == this) {
            return true;
        }
        return otherResidentRoom != null
                && (otherResidentRoom.getName().equals(getName())
                || otherResidentRoom.getRoomNumber().equals(getRoomNumber()));
    }

    /**
     * Returns true if both residents have the same identity and data fields.
     * This defines a stronger notion of equality between two residents.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ResidentRoom)) {
            return false;
        }

        ResidentRoom otherResidentRoom = (ResidentRoom) other;
        return otherResidentRoom.getName().equals(getName())
                && otherResidentRoom.getRoomNumber().equals(getRoomNumber());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, roomNumber);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(getRoomNumber());

        return builder.toString();
    }

}
