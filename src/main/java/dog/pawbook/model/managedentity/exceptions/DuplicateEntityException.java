package dog.pawbook.model.managedentity.exceptions;

/**
 * Signals that the operation will result in duplicate Entities.
 */
public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException() {
        super("Operation would result in duplicate entities");
    }
}
