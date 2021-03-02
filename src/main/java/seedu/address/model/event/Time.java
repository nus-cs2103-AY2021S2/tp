package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public LocalDateTime time;

    public Time(LocalDateTime time) {
        this.time = time;
    }
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        return this.time.format(timeFormatter);
    }
}
