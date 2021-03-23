package dog.pawbook.model.managedentity.exceptions;

/**
 * Signals that the operation will results in broken mutual references among the stored entities. Every entities' stored
 * IDs of others has to be maintained properly.
 */
public class BrokenReferencesException extends RuntimeException {
    public BrokenReferencesException() {
        super("Operation will results in broken references among entities.");
    }
}
