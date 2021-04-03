package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.tag.Tag;

/**
 * Represents an appointment in the appointment schedule
 * Guarantees: field values are validated, immutable.
 * TODO: convert doctor into Person field.
 */
public class AppointmentDisplay extends Appointment {
    // Data fields
    private final Patient patient;
    private final Doctor doctor;

    /**
     * Every field must be present and not null.
     */
    public AppointmentDisplay(Patient patient, Doctor doctor, Timeslot timeslot, Set<Tag> tags) {
        super(patient.getUuid(), doctor.getUuid(), timeslot, tags);
        requireAllNonNull(patient, doctor, timeslot, tags);
        this.patient = patient;
        this.doctor = doctor;
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

    //// Accessors
    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
