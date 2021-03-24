package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.person.Event;

public class EventBuilder {

    public static final LocalDate DEFAULT_DATE = LocalDate.of(2020, 5, 6);
    public static final LocalTime DEFAULT_TIME = null;
    public static final String DEFAULT_DESCRIPTION = "this is a description";

    private LocalDate date;
    private LocalTime time;
    private String description;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        date = DEFAULT_DATE;
        time = DEFAULT_TIME;
        description = DEFAULT_DESCRIPTION;
    }

    /**
     * Sets the {@code date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(LocalDate date) {
        this.date = LocalDate.from(date);
        return this;
    }

    /**
     * Sets the {@code time} of the {@code Event} that we are building.
     */
    public EventBuilder withTime(LocalTime time) {
        this.time = LocalTime.from(time);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Event build() {
        return new Event(date, time, description);
    }
}
