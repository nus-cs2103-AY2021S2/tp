package seedu.student.model.student.exceptions;

import seedu.student.commons.exceptions.IllegalValueException;

/**
 * Signals that the student with the specified matriculation number does not exist in VAX@NUS
 */

public class MatriculationNumberDoesNotExistException extends IllegalValueException {

    public MatriculationNumberDoesNotExistException(String message) {
        super(message);
    }
}
