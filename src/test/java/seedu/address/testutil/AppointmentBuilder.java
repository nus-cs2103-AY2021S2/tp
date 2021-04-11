package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final Patient DEFAULT_PATIENT = new PatientBuilder().build();
    public static final Doctor DEFAULT_DOCTOR = new DoctorBuilder().build();
    public static final Timeslot DEFAULT_TIMESLOT = new TimeslotBuilder().build();

    private UUID patientUuid;
    private UUID doctorUuid;
    private Timeslot timeslot;
    private Set<Tag> tags;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        patientUuid = new PatientBuilder(DEFAULT_PATIENT).build().getUuid();
        doctorUuid = new DoctorBuilder(DEFAULT_DOCTOR).build().getUuid();
        timeslot = new TimeslotBuilder(DEFAULT_TIMESLOT).build();
        tags = new HashSet<>();
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        patientUuid = appointmentToCopy.getPatientUuid();
        doctorUuid = appointmentToCopy.getDoctorUuid();
        timeslot = new TimeslotBuilder(appointmentToCopy.getTimeslot()).build();
        tags = new HashSet<>(appointmentToCopy.getTags());
    }

    /**
     * Sets the {@code patient} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatient(Patient patient) {
        this.patientUuid = new PatientBuilder(patient).build().getUuid();
        return this;
    }

    /**
     * Sets the {@code doctor} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDoctor(Doctor doctor) {
        this.doctorUuid = new DoctorBuilder(doctor).build().getUuid();
        return this;
    }

    /**
     * Sets the {@code timeslot} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimeslot(Timeslot timeslot) {
        this.timeslot = new TimeslotBuilder(timeslot).build();
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public AppointmentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public Appointment build() {
        return new Appointment(patientUuid, doctorUuid, timeslot, tags);
    }

}
