package seedu.address.model.schedule;

import java.util.Objects;

import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.event.Event;

/**
 * Schedule class to store schedule objects to represent tutee's tuition related schedule
 */
public class Schedule extends Event {

    private final Title title;
    private final Description description;

    /**
     * Primary constructor for schedule class.
     *
     * @param title       Title of the schedule
     * @param timeFrom    Start time of schedule
     * @param timeTo      End time of schedule
     * @param description Description of schedule
     */
    public Schedule(Title title, AppointmentDateTime timeFrom, AppointmentDateTime timeTo, Description description) {
        super(timeFrom, timeTo);
        this.title = title;
        this.description = description;
    }

    /**
     * Checks whether given schedule is valid.
     *
     * @param schedule Schedule to check
     * @return Boolean representing whether given schedule is valid
     */
    public static boolean isValidSchedule(Schedule schedule) {
        return Title.isValidTitle(schedule.getTitle().value)
                && Description.isValidDescription(schedule.getDescription().value)
                && AppointmentDateTime.isValidDateTime(schedule.getTimeFrom().toStorageString())
                && AppointmentDateTime.isValidDateTime(schedule.getTimeTo().toStorageString());
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.title.value, super.toString());
    }

    /**
     * Returns true if both schedule have the same title.
     * This defines a weaker notion of equality between two schedules.
     */
    public boolean isSameSchedule(Schedule otherSchedule) {
        if (otherSchedule == this) {
            return true;
        }

        return otherSchedule != null
                && otherSchedule.getTitle().equals(getTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule that = (Schedule) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description)
                && Objects.equals(timeFrom, that.timeFrom)
                && Objects.equals(timeTo, that.timeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, timeFrom, timeTo);
    }
}
