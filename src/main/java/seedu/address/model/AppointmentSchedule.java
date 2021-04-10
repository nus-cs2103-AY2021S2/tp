package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.UUID;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.NonConflictingAppointmentList;
import seedu.address.model.appointment.exceptions.AppointmentDoctorNotInDoctorRecordsException;
import seedu.address.model.appointment.exceptions.AppointmentPatientNotInPatientRecordsException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * Wraps all data at the appointment-schedule level
 * Conflicting appointments are not allowed
 * @see Appointment#hasConflict(Appointment)
 */
public class AppointmentSchedule implements ReadOnlyAppointmentSchedule {
    private final NonConflictingAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        appointments = new NonConflictingAppointmentList();
    }

    public AppointmentSchedule() {}

    /**
     * Creates an AppointmentSchedule using the Appointments in the {@code toBeCopied}
     */
    public AppointmentSchedule(ReadOnlyAppointmentSchedule toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain conflicting appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code AppointmentSchedule} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentSchedule newData) {
        requireNonNull(newData);

        setAppointments(newData.getAppointmentList());
    }

    //// appointment-level operations

    @Override
    public void checkAppointmentScheduleValidity(
                ReadOnlyAddressBook<Patient> patientRecords,
                ReadOnlyAddressBook<Doctor> doctorRecords)
                throws AppointmentPatientNotInPatientRecordsException,
                AppointmentDoctorNotInDoctorRecordsException {
        appointments.checkAppointmentPatientValidity(patientRecords.getPersonList());
        appointments.checkAppointmentDoctorValidity(doctorRecords.getPersonList());
    }

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists
     * in the appointment schedule.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Returns true if a patient has existing appointments in the appointment schedule.
     */
    public boolean hasPatientInSchedule(Patient patient) {
        return appointments.hasPatientInSchedule(patient);
    }

    /**
     * Returns true if a doctor has existing appointments in the appointment schedule.
     */
    public boolean hasDoctorInSchedule(Doctor doctor) {
        return appointments.hasDoctorInSchedule(doctor);
    }

    /**
     * Returns true if an appointment has a conflict with {@code appointment} exists
     * in the appointment schedule.
     */
    public boolean hasConflict(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.hasConflict(appointment);
    }

    /**
     * Returns true if an appointment has a conflict with {@code appointment} exists
     * in the appointment schedule.
     */
    public boolean hasConflictExcludingTarget(Appointment target, Appointment appointment) {
        requireNonNull(appointment);
        return appointments.hasConflictExcludingTarget(target, appointment);
    }

    /**
     * Removes {@code toRemove} from this {@code AppointmentSchedule}.
     * {@code toRemove} must exist in the appointment schedule.
     */
    public void removeAppointment(Appointment toRemove) {
        appointments.remove(toRemove);
    }

    /**
     * Deletes all appointments associated with the input {@code patientUuid} from the appointment schedule.
     */
    public void deletePatientAppointments(UUID patientUuid) {
        appointments.deletePatientAppointments(patientUuid);
    }

    /**
     * Deletes all appointments associated with the input {@code doctor} from the appointment schedule.
     */
    public void deleteDoctorAppointments(UUID doctorUuid) {
        appointments.deleteDoctorAppointments(doctorUuid);
    }

    /**
     * Adds an appointment to the address book.
     * The appointment must not have conflicts with the existing appointments in the
     * appointment schedule
     */
    public void addAppointment(Appointment toAdd) {
        appointments.add(toAdd);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the AppointmentSchedule.
     * The appointment {@code editedAppointment} must not have conflicts with any existing
     * appointments in the appointment schedule.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    //// util methods
    @Override
    public String toString() {
        return appointments.asUnmodifiableObservableList().size() + " appointments";
        // TODO: refine later
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentSchedule // instanceof handles nulls
                && appointments.equals(((AppointmentSchedule) other).appointments));
    }

    @Override
    public int hashCode() {
        return appointments.hashCode();
    }

}
