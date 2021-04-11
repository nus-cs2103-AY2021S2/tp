package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.appointment.Timeslot;

/**
 * A utility class to help with building Timeslot objects.
 */
public class TimeslotBuilder {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm");
    public static final LocalDateTime DEFAULT_START = LocalDateTime.parse("2021-01-01 00:00",
            DATE_TIME_FORMATTER);
    public static final LocalDateTime DEFAULT_END = LocalDateTime.parse("2021-01-02 00:00",
            DATE_TIME_FORMATTER);

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a {@code TimeslotBuilder} with the default details.
     */
    public TimeslotBuilder() {
        start = DEFAULT_START;
        end = DEFAULT_END;
    }

    /**
     * Initializes the TimeslotBuilder with the data of {@code timeslotToCopy}.
     */
    public TimeslotBuilder(Timeslot timeslotToCopy) {
        start = timeslotToCopy.getStart();
        end = timeslotToCopy.getEnd();
    }

    /**
     * Sets the {@code start} of the {@code Timeslot} that we are building.
     */
    public TimeslotBuilder withStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    /**
     * Sets the {@code end} of the {@code Timeslot} that we are building.
     */
    public TimeslotBuilder withEnd(LocalDateTime end) {
        this.end = end;
        return this;
    }

    public Timeslot build() {
        return new Timeslot(start, end);
    }

}
