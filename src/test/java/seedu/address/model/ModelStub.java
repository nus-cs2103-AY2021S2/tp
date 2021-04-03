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
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    //=========== PatientRecords ================================================================================
    @Override
    public Path getPatientRecordsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPatientRecordsFilePath(Path patientRecordsFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPatient(Patient person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPatientRecords(ReadOnlyAddressBook<Patient> newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook<Patient> getPatientRecords() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasConflictingUuid(UUID uuid) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePatient(Patient target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        throw new AssertionError("This method should not be called.");
    }

    public ObservableList<Patient> getFilteredPatientList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPatientList(Predicate<? super Patient> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    //=========== PatientRecords ================================================================================
    @Override
    public Path getDoctorRecordsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDoctorRecordsFilePath(Path doctorRecordsFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDoctorRecords(ReadOnlyAddressBook<Doctor> newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook<Doctor> getDoctorRecords() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addDoctor(Doctor doctor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasDoctor(Doctor dcotor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteDoctor(Doctor target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredDoctorList(Predicate<? super Doctor> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    //=========== AppointmentSchedule ========================================================================
    @Override
    public Path getAppointmentScheduleFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointmentScheduleFilePath(Path appointmentScheduleFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointmentSchedule(ReadOnlyAppointmentSchedule newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAppointmentSchedule getAppointmentSchedule() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPatientInAppointmentSchedule(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasDoctorInAppointmentSchedule(Doctor doctor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasConflictingAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasConflictingAppointmentExcludingTarget(Appointment target, Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePatientAppointments(UUID patientUuid) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteDoctorAppointments(UUID doctorUuid) {
        throw new AssertionError("This method should not be called");
    }

    @Override
    public void deleteAppointment(Appointment target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
