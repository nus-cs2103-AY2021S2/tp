package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path patientRecordsFilePath = Paths.get("data" , "PatientRecords.json");
    private Path appointmentScheduleFilePath = Paths.get("data", "AppointmentSchedule.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setPatientRecordsFilePath(newUserPrefs.getPatientRecordsFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    //=========== AddressBook ================================================================================
    public Path getPatientRecordsFilePath() {
        return patientRecordsFilePath;
    }

    public void setPatientRecordsFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.patientRecordsFilePath = addressBookFilePath;
    }

    //=========== AppointmentSchedule ========================================================================
    public Path getAppointmentScheduleFilePath() {
        return appointmentScheduleFilePath;
    }

    public void setAppointmentScheduleFilePath(Path appointmentScheduleFilePath) {
        requireNonNull(appointmentScheduleFilePath);
        this.appointmentScheduleFilePath = appointmentScheduleFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && patientRecordsFilePath.equals(o.patientRecordsFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, patientRecordsFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + patientRecordsFilePath);
        return sb.toString();
    }

}
