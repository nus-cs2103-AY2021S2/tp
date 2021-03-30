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
 * Duplicates are not allowed (by .isSameEvent comparison)
 */
public class EventTracker {
    private final ScheduleTracker scheduleTracker;
    private final AppointmentBook appointmentBook;
    private final EventList events;

    {
        events = new EventList();
    }

    /**
     * Creates an AppointmentBook using the Appointments in the {@code toBeCopied}
     */
    public EventTracker(ReadOnlyAppointmentBook appointmentBook, ReadOnlyScheduleTracker scheduleTracker) {
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.scheduleTracker = new ScheduleTracker(scheduleTracker);
        resetData(appointmentBook, scheduleTracker);
    }

    /**
     * Resets the existing data of this {@code AppointmentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentBook appointmentBook, ReadOnlyScheduleTracker scheduleTracker) {
        requireNonNull(appointmentBook);
        requireNonNull(scheduleTracker);
        setEvents(appointmentBook.getAppointmentList(), scheduleTracker.getScheduleList());
    }

    public void setEvents(List<Appointment> appointments, List<Schedule> schedules) {
        this.events.setEvents(appointments, schedules);
    }

    /**
     * Returns true if a person with the same identity as {@code appointment} exists in the appointment book.
     */
    public boolean hasClashingDateTime(Event event) {
        requireNonNull(event);
        return events.containsDate(event);
    }

    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

}
