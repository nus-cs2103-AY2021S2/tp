package dog.pawbook.model.managedentity.exceptions;

/**
 * Signals that the operation will result in duplicate Owners (Owners are considered duplicates if they have the same
 * identity).
 */
public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException() {
        super("Operation would result in duplicate entities");
    }
}
