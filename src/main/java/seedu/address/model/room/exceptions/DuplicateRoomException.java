package seedu.address.model.room.exceptions;

/**
 * Signals that the operation will result in duplicate Rooms (Rooms are considered duplicates if they have the same
 * identity).
 */
public class DuplicateRoomException extends RuntimeException {
    public DuplicateRoomException() {
        super("Operation would result in duplicate rooms.");
    }
}
