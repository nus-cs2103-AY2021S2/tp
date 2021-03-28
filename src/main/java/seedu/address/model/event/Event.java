package seedu.address.model.event;

import seedu.address.model.appointment.AppointmentDateTime;

/**
 * Event class is an abstract superclass of {@code Appointment} and {@code Schedule}.
 */
public abstract class Event {
    /* Temporarily keeping it as AppointmentDateTime as unsure how big the change could be */
    protected final AppointmentDateTime timeFrom;
    protected final AppointmentDateTime timeTo;

    /**
     * Primary constructor for Event class.
     *
     * @param timeFrom Start time of event
     * @param timeTo   End time of event
     */
    public Event(AppointmentDateTime timeFrom, AppointmentDateTime timeTo) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public AppointmentDateTime getTimeFrom() {
        return timeFrom;
    }

    public AppointmentDateTime getTimeTo() {
        return timeTo;
    }

    @Override
    public String toString() {
        return String.format("from %s to %s", this.timeFrom.toString(), this.timeTo.toString());
    }
}
