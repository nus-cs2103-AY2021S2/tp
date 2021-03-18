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
 * TODO: convert doctor into Person field.
 */
public class Appointment implements Comparable<Appointment> {
    // Data fields
    private final Person patient;
    private final String doctor;
    private final Timeslot timeslot;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Appointment(Person patient, String doctor, Timeslot timeslot, Set<Tag> tags) {
        requireAllNonNull(patient, doctor, timeslot, tags);
        this.patient = patient;
        this.doctor = doctor;
        this.timeslot = timeslot;
        this.tags.addAll(tags);
    }

    //// Accessors
    public Person getPatient() {
        return patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public LocalDateTime getAppointmentStart() {
        return timeslot.getStart();
    }

    public LocalDateTime getAppointmentEnd() {
        return timeslot.getEnd();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Remove brackets from Tags and converting the Set of Tags to Strings
     */
    public Set<String> convertToStringSet(Set<Tag> setTag) {
        HashSet<String> stringSet = new HashSet<>();
        setTag.forEach(tag -> stringSet.add(tag.toRawString()));
        return stringSet;
    }

    //// Boolean checks
    /**
     * Defines the default sorting criterion by Appointment datetime.
     */
    @Override
    public int compareTo(Appointment otherAppointment) {
        return getTimeslot().compareTo(otherAppointment.getTimeslot());
    }

    /**
     * Returns whether there is a scheduling conflict between this appointment and the other.
     */
    public boolean hasConflict(Appointment toCheck) {
        return (patient.equals(toCheck.getPatient())
                || doctor.equals(toCheck.getDoctor()))
                && getTimeslot().hasOverlap(toCheck.getTimeslot());
    }

    public boolean isDue() {
        return getAppointmentStart().isBefore(LocalDateTime.now());
    }

    public boolean hasExpired() {
        return getAppointmentEnd().isBefore(LocalDateTime.now());
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
                && otherAppointment.getTimeslot().equals(getTimeslot())
                && otherAppointment.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, doctor, timeslot, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient: ")
                .append(getPatient().toString())
                .append("; Doctor: ")
                .append(getDoctor().toString())
                .append("; Timeslot: ")
                .append(getTimeslot().toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
