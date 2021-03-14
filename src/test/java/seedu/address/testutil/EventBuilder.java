package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_DESCRIPTION = "Event Description";
    public static final Interval DEFAULT_INTERVAL = Interval.NONE;
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2021, 1, 1);
    public static final Boolean DEFAULT_IS_DONE = false;

    private String description;
    private Interval interval;
    private LocalDate at;
    private Boolean isDone;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        description = DEFAULT_DESCRIPTION;
        interval = DEFAULT_INTERVAL;
        at = DEFAULT_DATE;
        isDone = DEFAULT_IS_DONE;
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        description = eventToCopy.getDescription();
        interval = eventToCopy.getRecurrence();
        at = eventToCopy.getAt();
        isDone = eventToCopy.getIsDone();
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code Interval} of the {@code Event} that we are building.
     */
    public EventBuilder withInterval(Interval interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Sets the {@code at} date of the {@code Event} that we are building.
     */
    public EventBuilder withAtDate(LocalDate date) {
        this.at = date;
        return this;
    }

    /**
     * Sets the {@code isDone} status of the {@code Event} that we are building.
     */
    public EventBuilder withIsDone(Boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Builds the {@code Event} object.
     *
     * @return {@code Event}.
     */
    public Event build() {
        return new Event(description, interval, at);
    }

}
