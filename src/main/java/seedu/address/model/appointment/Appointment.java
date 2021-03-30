package seedu.address.model.appointment;

import java.util.Objects;

import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.subject.SubjectName;

//TODO Add restriction for add_appointment to ensure that tutor must exist in
// address_book before adding appointment

/**
 * Appointment class to store appointment objects to represent tutee and tutor relations
 */
public class Appointment extends Event {

    private final Name name;
    private final SubjectName subject;
    private final Address location;

    /**
     * Primary constructor for appointment class.
     *
     * @param name      Name of tutor.
     * @param subject   Subject tutor is teaching to tutee.
     * @param timeFrom  Start Time of appointment
     * @param timeTo    End Time of appointment
     * @param location  Location of teaching venue
     */
    public Appointment(Name name, SubjectName subject, AppointmentDateTime timeFrom,
                       AppointmentDateTime timeTo, Address location) {
        super(timeFrom, timeTo);
        this.name = name;
        this.subject = subject;
        this.location = location;
    }

    public Name getName() {
        return name;
    }

    public SubjectName getSubject() {
        return subject;
    }

    public Address getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("Appointment with Tutor (%s) %s",
                this.name.fullName, super.toString());
    }

    /**
     * Returns true if both appointment have the same datetime.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && this.timeFrom.equals(otherAppointment.timeFrom)
                && this.timeTo.equals(otherAppointment.timeTo);
    }

    /**
     * Checks whether given appointment is valid.
     * @param appointment Appointment to check
     * @return Boolean representing whether given appointment is valid
     */
    public static boolean isValidAppointment(Appointment appointment) {

        return Name.isValidName(appointment.getName().fullName)
                && SubjectName.isValidName(appointment.getSubject().name)
                && AppointmentDateTime.isValidDateTime(appointment.getTimeFrom().toDateString())
                && AppointmentDateTime.isValidDateTime(appointment.getTimeTo().toDateString())
                && Address.isValidAddress(appointment.getLocation().toString());
    }

    /**
     * @return Length of Appointment in hours.
     */
    public int getDifferenceInMinutes() {
        return (int) this.timeFrom.getDifferenceInMinutes(this.timeTo.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Appointment that = (Appointment) o;
        return Objects.equals(name, that.name) && Objects.equals(subject, that.subject)
                && Objects.equals(timeFrom, that.timeFrom)
                && Objects.equals(timeTo, that.timeTo)
                && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subject, timeFrom, timeTo, location);
    }
}
