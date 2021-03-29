package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Room's number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoomNumber(String)}
 */
public class RoomNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "Room numbers should only contain positive integers and dashes, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\d{2}-\\d{3}";

    public final String roomNumber;

    /**
     * Constructs a {@code RoomNumber}.
     *
     * @param roomNumber A valid room number.
     */
    public RoomNumber(String roomNumber) {
        requireNonNull(roomNumber);
        checkArgument(isValidRoomNumber(roomNumber), MESSAGE_CONSTRAINTS);
        this.roomNumber = roomNumber;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRoomNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return roomNumber;
    }


    /**
     * Returns true if a room has the same number.
     * This defines a weaker notion of equality between two rooms.
     */
    public boolean isSameRoom(Room room) {
        return this.equals(room.getRoomNumber());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNumber // instanceof handles nulls
                && roomNumber.equals(((RoomNumber) other).roomNumber)); // state check
    }

    @Override
    public int hashCode() {
        return roomNumber.hashCode();
    }
}
