package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.LocalDate;

public class Meeting {
    private final LocalDate date;

    private final LocalTime time;
    private final String description;

    public Meeting(LocalDate date, String description) {
        requireAllNonNull(date, description);
        this.date = date;
        this.time = null;
        this.description = description;
    }

    public Meeting(LocalDate date, LocalTime time, String description) {
        requireAllNonNull(date, time, description);
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append("; ")
                .append(getTime())
                .append("; ")
                .append(getDescription());

        return builder.toString();
    }
}
