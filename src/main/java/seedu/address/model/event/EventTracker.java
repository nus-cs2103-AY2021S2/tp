package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;

/**
 * Wraps all data at the event tracker level
 * Duplicates are not allowed (by .isSameAppointment and .isSameSchedule comparison)
 */
public class EventTracker {
    private final ScheduleTracker scheduleTracker;
    private final AppointmentBook appointmentBook;
    private final EventList events;

    {
        events = new EventList();
    }

    /**
     * Creates an EventTracker using both {@code AppointmentBook} and {@code ScheduleTracker}.
     */
    public EventTracker(ReadOnlyAppointmentBook appointmentBook, ReadOnlyScheduleTracker scheduleTracker) {
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.scheduleTracker = new ScheduleTracker(scheduleTracker);
        resetData(appointmentBook, scheduleTracker);
    }

    /**
     * Resets the existing data of this {@code EventTracker} with {@code appointmentBook} and {@code scheduleTracker}.
     */
    public void resetData(ReadOnlyAppointmentBook appointmentBook, ReadOnlyScheduleTracker scheduleTracker) {
        requireNonNull(appointmentBook);
        requireNonNull(scheduleTracker);
        setEvents(appointmentBook.getAppointmentList(), scheduleTracker.getScheduleList());
    }

    /**
     * Replaces the contents of the event lists with {@code appointments} and {@code schedules}.
     * {@code persons} must not contain duplicate events.
     */
    public void setEvents(List<Appointment> appointments, List<Schedule> schedules) {
        this.events.setEvents(appointments, schedules);
    }

    /**
     * Returns true if an event have datetime clashes with existing records
     * in {@code AppointmentBook} or {@code ScheduleTracker}.
     */
    public boolean hasClashingDateTime(Event event) {
        requireNonNull(event);
        return events.containsDate(event);
    }

    /**
     * Returns an unmodifiable view of the event list.
     * This list will not contain any duplicate events.
     */
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

}
