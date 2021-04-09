package seedu.address.model.residentroom.exceptions;

/**
 * Signals that the operation will result in duplicate ResidentRoom (ResidentRoom are considered duplicates if
 * they have the same room number or same name in any of the existing ResidentRoom).
 */
public class DuplicateResidentRoomException extends RuntimeException {
    /**
     * Exception if either resident or room is found in ResidentRoom list.
     */
    public DuplicateResidentRoomException() {
        super("The resident or room has already been allocated."
                + "Please deallocate before proceeding");
    }
}
