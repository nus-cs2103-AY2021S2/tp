package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_DESCRIPTION = "Event Description";
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2021, 1, 1);
    public static final LocalTime DEFAULT_TIME = LocalTime.of(17, 30);
    public static final Boolean DEFAULT_IS_WEEKLY = false;

    private String description;
    private LocalDate date;
    private LocalTime time;
    private Boolean isWeekly;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        description = DEFAULT_DESCRIPTION;
        date = DEFAULT_DATE;
        time = DEFAULT_TIME;
        isWeekly = DEFAULT_IS_WEEKLY;
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        description = eventToCopy.getDescription();
        date = eventToCopy.getDate();
        time = eventToCopy.getTime();
        isWeekly = eventToCopy.getIsWeekly();
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Sets the {@code time} of the {@code Event} that we are building.
     */
    public EventBuilder withTime(LocalTime time) {
        this.time = time;
        return this;
    }

    /**
     * Sets the {@code isWeekly} status of the {@code Event} that we are building.
     */
    public EventBuilder withIsWeekly(Boolean isWeekly) {
        this.isWeekly = isWeekly;
        return this;
    }

    /**
     * Builds the {@code Event} object.
     *
     * @return {@code Event}.
     */
    public Event build() {
        return new Event(description, date, time, isWeekly);
    }

}
