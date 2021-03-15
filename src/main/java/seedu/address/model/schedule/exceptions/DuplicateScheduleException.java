package seedu.address.model.schedule.exceptions;

public class DuplicateScheduleException extends RuntimeException {
    public DuplicateScheduleException() {
        super("Operation would result in duplicate schedules");
    }
}
