package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Room's number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoomNumber(String)}
 */
public class RoomNumber implements Comparable<RoomNumber> {
    public static final String MESSAGE_CONSTRAINTS =
            "Room numbers should be formatted as such: XY-ABC, "
                    + "where XY can be any pair of digits except 00, and ABC can be any 3 digits.";

    public static final String VALIDATION_REGEX = "(?!00)\\d{2}(-\\d{3})";

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
     * Checks if a {@code String} is a valid Room Number.
     *
     * @param test {@code String} to test.
     * @return True if the Room Number {@code String} is valid.
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

    @Override
    public int compareTo(RoomNumber roomNumber) {
        return this.roomNumber.compareTo(roomNumber.roomNumber);
    }
}
