package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.person.Meeting;

public class MeetingBuilder {

    public static final LocalDate DEFAULT_DATE = LocalDate.of(2020, 5, 6);
    public static final LocalTime DEFAULT_TIME = LocalTime.of(10, 0);
    public static final String DEFAULT_DESCRIPTION = "this is a description";

    private LocalDate date;
    private LocalTime time;
    private String description;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        date = DEFAULT_DATE;
        time = DEFAULT_TIME;
        description = DEFAULT_DESCRIPTION;
    }

    /**
     * Sets the {@code date} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDate(LocalDate date) {
        this.date = LocalDate.from(date);
        return this;
    }

    /**
     * Sets the {@code time} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTime(LocalTime time) {
        this.time = LocalTime.from(time);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds an Meeting with the attributes from MeetingBuilder
     */
    public Meeting build() {
        return new Meeting(date, time, description);
    }
}
