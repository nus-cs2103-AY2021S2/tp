package seedu.student.model.appointment;

import static seedu.student.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import seedu.student.model.student.MatriculationNumber;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment implements Comparable<Appointment> {

    // Identity fields
    private final MatriculationNumber matriculationNumber;

    // Data fields
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(MatriculationNumber matriculationNumber, LocalDate date, LocalTime startTime,
                       LocalTime endTime) {
        requireAllNonNull(matriculationNumber, date, startTime, endTime);
        this.matriculationNumber = matriculationNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public MatriculationNumber getMatriculationNumber() {
        return matriculationNumber;
    }

    /**
     * Returns true if both appointments have the matriculation number.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null && otherAppointment.matriculationNumber.equals(matriculationNumber);
    }

    /**
     * Returns true if appointments overlap.
     */
    public boolean doesTimeOverlap(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }
        if (!otherAppointment.date.isEqual(date)) {
            return false;
        }
        return otherAppointment.startTime.isBefore(endTime) && otherAppointment.endTime.isAfter(startTime);
    }

    /**
     * Returns true if both appointments have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.date.isEqual(date) && otherAppointment.matriculationNumber.equals(matriculationNumber)
                && otherAppointment.startTime.equals(startTime) && otherAppointment.endTime.equals(endTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(matriculationNumber, date, startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Appointment: ");
        builder.append(getDate().toString())
                .append("; Matriculation Number: ")
                .append(matriculationNumber)
                .append("; Start Time: ")
                .append(getStartTime().toString())
                .append("; End Time: ")
                .append(getEndTime().toString());
        return builder.toString();
    }

    @Override
    public int compareTo(Appointment o) {
        int dateDifference = date.compareTo(o.date);
        if (dateDifference != 0) {
            return dateDifference;
        }
        return startTime.compareTo(o.startTime);
    }
}
