package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.EnumUtil;


/**
 * Represents a RoomType in the hostel management system.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RoomType {
    public static final String MESSAGE_CONSTRAINTS =
            "The valid options for Room Type are: " + String.join(", ", EnumUtil.getNames(RoomTypeOptions.class));

    public final RoomTypeOptions roomType;

    /**
     * Constructs a {@code RoomNumber}.
     *
     * @param roomType A valid room type.
     */
    public RoomType(String roomType) {
        roomType = roomType.toUpperCase();
        requireNonNull(roomType);
        checkArgument(isValidRoomType(roomType), MESSAGE_CONSTRAINTS);
        this.roomType = RoomTypeOptions.valueOf(roomType);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRoomType(String test) {
        try {
            RoomTypeOptions.valueOf(test);
            return true;
        } catch (IllegalArgumentException ex) {
            // Unable to parse
            return false;
        }
    }


    @Override
    public String toString() {
        return roomType.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomType // instanceof handles nulls
                && roomType.equals(((RoomType) other).roomType)); // state check
    }

    @Override
    public int hashCode() {
        return roomType.hashCode();
    }
}
