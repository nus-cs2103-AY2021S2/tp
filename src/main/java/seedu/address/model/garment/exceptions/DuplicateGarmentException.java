package seedu.address.model.garment.exceptions;

/**
 * Signals that the operation will result in duplicate Garments
 * (Garments are considered duplicates if they have the same
 * identity).
 */
public class DuplicateGarmentException extends RuntimeException {
    public DuplicateGarmentException() {
        super("Operation would result in duplicate garments");
    }
}
