package seedu.address.model.subject.exceptions;

public class DuplicateSubjectException extends RuntimeException {
    public DuplicateSubjectException() {
        super("Operation would result in duplicate subjects");
    }
}
