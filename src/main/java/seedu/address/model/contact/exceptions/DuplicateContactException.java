package seedu.address.model.contact.exceptions;

/**
 * Represents an error that occurs when an operation results in the creation of duplicate
 * contacts (contacts are considered duplicates if they have the same identity).
 */
public class DuplicateContactException extends RuntimeException {
    public DuplicateContactException() {
        super("Operation would result in duplicate contacts");
    }
}
