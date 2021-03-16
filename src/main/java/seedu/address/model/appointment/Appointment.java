package seedu.address.model.appointment;

import java.util.Objects;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.subject.SubjectName;

/**
 * Appointment class to store appointment objects to represent tutee and tutor relations
 */
public class Appointment {

    private final Email email;
    private final SubjectName subject;
    private final AppointmentDateTime dateTime;
    private final Address location;

    /**
     * Primary constructor for appointment class.
     *
     * @param email    Email of tutor.
     * @param subject  Subject tutor is teaching to tutee.
     * @param dateTime Date and time of appointment
     * @param location Location of teaching venue
     */
    public Appointment(Email email, SubjectName subject, AppointmentDateTime dateTime,
            Address location) {
        this.email = email;
        this.subject = subject;
        this.dateTime = dateTime;
        this.location = location;
    }

    public Email getEmail() {
        return email;
    }

    public SubjectName getSubject() {
        return subject;
    }

    public AppointmentDateTime getDateTime() {
        return dateTime;
    }

    public Address getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("Appointment with Tutor (%s) at %s", this.email.value, dateTime.toString());
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
                && this.dateTime.equals(otherAppointment.dateTime);
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
        return Objects.equals(email, that.email) && Objects.equals(subject, that.subject)
                && Objects.equals(dateTime, that.dateTime) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, subject, dateTime, location);
    }
}
