package seedu.address.model;

import java.nio.file.Path;
import java.util.UUID;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<Doctor> PREDICATE_SHOW_ALL_DOCTORS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //=========== PatientRecords ================================================================================
    /**
     * Returns the user prefs' PatientRecords file path.
     */
    Path getPatientRecordsFilePath();

    /**
     * Sets the user prefs' PatientRecords file path.
     */
    void setPatientRecordsFilePath(Path patientRecordsFilePath);

    /**
     * Replaces PatientRecords data with the data in {@code patientRecords}.
     */
    void setPatientRecords(ReadOnlyAddressBook<Patient> patientRecords);

    /** Returns the PatientRecords */
    ReadOnlyAddressBook<Patient> getPatientRecords();

    /**
     * Returns true if model contains a conflicting UUID.
     * (which is not likely to happen, but just in case)
     */
    boolean hasConflictingUuid(UUID uuid);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The person must exist in the PatientRecords.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code person} must not already exist in the PatientRecords.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given Patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the PatientRecords.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the PatientRecords.
     */
    void setPatient(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<? super Patient> predicate);

    //=========== DoctorRecords ================================================================================
    /**
     * Returns the user prefs' DoctorRecords file path.
     */
    Path getDoctorRecordsFilePath();

    /**
     * Sets the user prefs' DoctorRecords file path.
     */
    void setDoctorRecordsFilePath(Path doctorRecordsFilePath);

    /**
     * Replaces DoctorRecords data with the data in {@code doctoRecords}.
     */
    void setDoctorRecords(ReadOnlyAddressBook<Doctor> doctorRecords);

    /** Returns the AddressBook */
    ReadOnlyAddressBook<Doctor> getDoctorRecords();

    /**
     * Returns true if a Doctor with the same identity as {@code doctor} exists in the DoctorRecords.
     */
    boolean hasDoctor(Doctor doctor);

    /**
     * Deletes the given doctor.
     * The person must exist in the DoctorRecords.
     */
    void deleteDoctor(Doctor target);

    /**
     * Adds the given doctor.
     * {@code doctor} must not already exist in the DoctorRecords.
     */
    void addDoctor(Doctor doctor);

    /**
     * Replaces the given person {@code target} with {@code editedDoctor}.
     * {@code target} must exist in the DoctorRecords.
     * The doctor identity of {@code editedDoctor} must not be the same as another existing doctor in the DoctorRecords.
     */
    void setDoctor(Doctor target, Doctor editedDoctor);

    /** Returns an unmodifiable view of the filtered doctor list */
    ObservableList<Doctor> getFilteredDoctorList();

    /**
     * Updates the filter of the filtered doctor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDoctorList(Predicate<? super Doctor> predicate);

    //=========== AppointmentSchedule ========================================================================
    /**
     * Returns the user prefs' appointment schedule file path.
     */
    Path getAppointmentScheduleFilePath();

    /**
     * Sets the user prefs' appointment schedule file path.
     */
    void setAppointmentScheduleFilePath(Path appointmentScheduleFilePath);

    /**
     * Replaces appointment schedule book data with the data in {@code appointmentSchedule}.
     */
    void setAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule);

    /** Returns the AppointmentSchedule */
    ReadOnlyAppointmentSchedule getAppointmentSchedule();

    /**
     * Returns true if patient has existing appointments in the appointment schedule.
     */
    boolean hasPatientInAppointmentSchedule(Patient patient);

    /**
     * Returns true if doctor has existing appointments in the appointment schedule.
     */
    boolean hasDoctorInAppointmentSchedule(Doctor doctor);

    /**
     * Returns true if an appointment that conflicts with {@code appointment} exists in the appointment schedule.
     */
    boolean hasConflictingAppointment(Appointment appointment);

    /**
     * Returns true if an appointment that conflicts with {@code appointment}
     * exists in the appointment schedule excluding the target.
     */
    boolean hasConflictingAppointmentExcludingTarget(Appointment target, Appointment appointment);


    /**
     * Deletes the given appointment {@code target}.
     * The appointment must exist in the appointment schedule.
     */
    void deleteAppointment(Appointment target);

    /**
     * Deletes all appointments associated with the input {@code patient} from the appointment schedule.
     */
    void deletePatientAppointments(UUID patientUuid);

    /**
     * Deletes all appointments associated with the input {@code doctor} from the appointment schedule.
     */
    void deleteDoctorAppointments(UUID doctorUuid);

    /**
     * Adds the given appointment.
     * {@code appointment} must not be in conflict with existing appointments in the appointment schedule.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the appointment schedule.
     * The {@code editedAppointment} must not be in conflict with another appointment in the appointment schedule
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);
}
