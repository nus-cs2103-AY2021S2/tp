package seedu.address.testutil;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;

/**
 * A utility class to help with building AppointmentBook objects.
 * Example usage: <br>
 * {@code AppointmentBook ab = new AppointmentBookBuilder().withAppointment("John", "Doe").build();}
 */
public class ScheduleTrackerBuilder {

    private ScheduleTracker scheduleTracker;

    public ScheduleTrackerBuilder() {
        scheduleTracker = new ScheduleTracker();
    }

    public ScheduleTrackerBuilder(ScheduleTracker scheduleTracker) {
        this.scheduleTracker = scheduleTracker;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ScheduleTrackerBuilder withSchedule(Schedule schedule) {
        scheduleTracker.addSchedule(schedule);
        return this;
    }

    public ScheduleTracker build() {
        return scheduleTracker;
    }
}
