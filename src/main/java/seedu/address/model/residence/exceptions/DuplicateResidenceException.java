package seedu.address.model.residence.exceptions;

/** p
 * Signals that the operation will result in duplicate Residences (Residences are considered duplicates
 * if they have the same identity).
 */

public class DuplicateResidenceException extends RuntimeException {
    public DuplicateResidenceException() {
        super("Operation would result in duplicate residences");
    }
}
