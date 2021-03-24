package seedu.address.model.grade.exceptions;

/**
 * Signals that the operation will result in duplicate Grades (Grades are considered duplicates if they have the same
 * identity).
 */
public class DuplicateGradeException extends RuntimeException {
    public DuplicateGradeException() {
        super("Operation would result in duplicate grades");
    }
}
