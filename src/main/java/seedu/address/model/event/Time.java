package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private LocalDateTime time;

    /**
     * Constructs an {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Returns the time in a string.
     */
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        return this.time.format(timeFormatter);
    }
}
