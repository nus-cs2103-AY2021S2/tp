package seedu.address.model.alias.exceptions;

/**
 * Signals that the operation will result in duplicate Aliases (Aliases are considered duplicates if they have the same
 * alias name).
 */
public class DuplicateAliasException extends RuntimeException {
    public DuplicateAliasException() {
        super("Operation would result in duplicate aliases");
    }
}
