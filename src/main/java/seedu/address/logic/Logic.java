package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentSchedule;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    //=========== PatientRecords ================================================================================
    /**
     * Returns the PatientRecords.
     *
     * @see seedu.address.model.Model#getPatientRecords()
     */
    ReadOnlyAddressBook<Patient> getPatientRecords();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Returns the user prefs' patient records file path.
     */
    Path getPatientRecordsFilePath();

    //=========== DoctorRecords ================================================================================
    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getDoctorRecords()
     */
    ReadOnlyAddressBook<Doctor> getDoctorRecords();

    /** Returns an unmodifiable view of the filtered list of doctors */
    ObservableList<Doctor> getFilteredDoctorList();

    /**
     * Returns the user prefs' doctor records file path.
     */
    Path getDoctorRecordsFilePath();

    //=========== AppointmentSchedule ========================================================================
    /**
     * Returns the AppointmentSchedule
     *
     * @see seedu.address.model.Model#getAppointmentSchedule()
     */
    ReadOnlyAppointmentSchedule getAppointmentSchedule();

    /** Returns an unmodifiable view of the filtered list of appointments */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns the user prefs' appointment schedule file path.
     */
    Path getAppointmentScheduleFilePath();

}
