package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.AppointmentDoctorNotInDoctorRecordsException;
import seedu.address.model.appointment.exceptions.AppointmentPatientNotInPatientRecordsException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

public interface ReadOnlyAppointmentSchedule {
    /**
     * Returns an unmodifiable view of the appointments list.
     * This list will not contain any conflicting appointments.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Checks the AppointmentSchedule to ensure the validity of its patient and doctor data.
     *
     * @param patientRecords the current list of Patients
     * @param doctorRecords the current list of Doctors
     * @throws AppointmentPatientNotInPatientRecordsException if a patient's data in
     * appointmentSchedule does not match any patient in the patientRecord
     * @throws AppointmentDoctorNotInDoctorRecordsException if a doctor's data in
     * appointmentSchedule does not match any doctor in the doctorRecord
     */
    void checkAppointmentScheduleValidity(
            ReadOnlyAddressBook<Patient> patientRecords,
            ReadOnlyAddressBook<Doctor> doctorRecords) throws
            AppointmentPatientNotInPatientRecordsException,
            AppointmentDoctorNotInDoctorRecordsException;
}
