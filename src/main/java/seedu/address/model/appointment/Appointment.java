package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents an appointment in the appointment schedule
 * Guarantees: field values are validated, immutable.
 */
public class Appointment implements Comparable<Appointment> {
    // Data fields
    private final Person patient;
    private final Person doctor;
    private final LocalDateTime dateTime;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Appointment(Person patient, Person doctor, LocalDateTime dateTime, Set<Tag> tags) {
        requireAllNonNull(patient, doctor, dateTime, tags);
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.tags.addAll(tags);
    }

    public Person getPatient() {
        return patient;
    }

    public Person getDoctor() {
        return doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Defines the default sorting criterion by Appointment datetime.
     */
    public int compareTo(Appointment otherAppointment) {
        if (getDateTime().equals(otherAppointment.getDateTime())) {
            return 0;
        } else if (getDateTime().isBefore(otherAppointment.getDateTime())) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Returns whether there is a scheduling conflict between this appointment and the other.
     */
    public boolean hasConflict(Appointment toCheck) {
        return (patient.equals(toCheck.getPatient())
                || doctor.equals(toCheck.getDoctor()))
                && dateTime.equals(toCheck.getDateTime());
    }

    public boolean hasPassed() {
        return getDateTime().isBefore(LocalDateTime.now());
    }

    /**
     * Returns true if both appointments have the same data fields.
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
        return otherAppointment.getPatient().equals(getPatient())
                && otherAppointment.getDoctor().equals(getDoctor())
                && otherAppointment.getDateTime().equals(getDateTime())
                && otherAppointment.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, doctor, dateTime, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient: ")
                .append(getPatient().toString())
                .append("; Doctor: ")
                .append(getDoctor().toString())
                .append("; DateTime: ")
                .append(getDateTime().toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
