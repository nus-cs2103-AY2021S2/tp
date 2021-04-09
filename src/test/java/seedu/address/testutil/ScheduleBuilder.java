package seedu.address.testutil;

import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.schedule.Schedule;

/**
 * A utility class to help with building Schedule objects.
 */
public class ScheduleBuilder {

    public static final String DEFAULT_TITLE = "Maths Homework";
    public static final String DEFAULT_DESCRIPTION = "Chapter 5 Page 841";
    public static final String DEFAULT_TIMEFROM = "2021-05-24 12:00PM";
    public static final String DEFAULT_TIMETO = "2021-05-24 2:00PM";

    private Title title;
    private Description description;
    private AppointmentDateTime timeFrom;
    private AppointmentDateTime timeTo;

    /**
     * Creates an {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        title = new Title(DEFAULT_TITLE);
        timeFrom = new AppointmentDateTime(DEFAULT_TIMEFROM);
        timeTo = new AppointmentDateTime(DEFAULT_TIMETO);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ScheduleBuilder with the data of {@code scheduleToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        title = scheduleToCopy.getTitle();
        timeFrom = scheduleToCopy.getTimeFrom();
        timeTo = scheduleToCopy.getTimeTo();
        description = scheduleToCopy.getDescription();
    }

    /**
     * Sets the {@code Title} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code timeFrom} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTimeFrom(String dateTime) {
        this.timeFrom = new AppointmentDateTime(dateTime);
        return this;
    }

    /**
     * Parses the {@code timeTo} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withTimeTo(String dateTime) {
        this.timeTo = new AppointmentDateTime(dateTime);
        return this;
    }

    public Schedule build() {
        return new Schedule(title, timeFrom, timeTo, description);
    }
}
