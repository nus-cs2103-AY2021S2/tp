package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.person.DoctorMap;
import seedu.address.model.person.PatientMap;
import seedu.address.model.tag.Tag;

/**
 * Represents an appointment in the appointment schedule
 * Guarantees: field values are validated, immutable.
 * TODO: convert doctor into Person field.
 */
public class Appointment implements Comparable<Appointment> {
    // Data fields
    private final UUID patientUuid;
    private final UUID doctorUuid;
    private final Timeslot timeslot;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Appointment(UUID patientUuid, UUID doctorUuid, Timeslot timeslot, Set<Tag> tags) {
        requireAllNonNull(patientUuid, doctorUuid, timeslot, tags);
        this.patientUuid = patientUuid;
        this.doctorUuid = doctorUuid;
        this.timeslot = timeslot;
        this.tags.addAll(tags);
    }

    //// Accessors
    public UUID getPatientUuid() {
        return patientUuid;
    }

    public UUID getDoctorUuid() {
        return doctorUuid;
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
        return (patientUuid.equals(toCheck.getPatientUuid())
                || doctorUuid.equals(toCheck.getDoctorUuid()))
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
        return otherAppointment.getPatientUuid().equals(getPatientUuid())
                && otherAppointment.getDoctorUuid().equals(getDoctorUuid())
                && otherAppointment.getTimeslot().equals(getTimeslot())
                && otherAppointment.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientUuid, doctorUuid, timeslot, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient: ")
                .append(PatientMap.getPatientFromUuid(getPatientUuid()))
                .append("; Doctor: ")
                .append(DoctorMap.getDoctorFromUuid(getDoctorUuid()))
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
