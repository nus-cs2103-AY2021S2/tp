package seedu.budgetbaby.model.month.exception;

/**
 * Signals that the operation will result in duplicate Months (Months are considered duplicates if they have the same
 * identity).
 */
public class DuplicateMonthException extends RuntimeException {
    public DuplicateMonthException() {
        super("Operaton would result in duplicate months");
    }
}

