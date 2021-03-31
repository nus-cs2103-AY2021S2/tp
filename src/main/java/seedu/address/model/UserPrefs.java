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
    private Path tutorBookFilePath = Paths.get("data" , "tutorbook.json");
    private Path appointmentBookFilePath = Paths.get("data", "appointmentBook.json");
    private Path gradeBookFilePath = Paths.get("data" , "gradeBook.json");
    private Path scheduleTrackerFilePath = Paths.get("data" , "scheduleTracker.json");
    private Path reminderTrackerFilePath = Paths.get("data" , "reminderTracker.json");

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
        setTutorBookFilePath(newUserPrefs.getTutorBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getTutorBookFilePath() {
        return tutorBookFilePath;
    }

    public Path getAppointmentBookFilePath() {
        return appointmentBookFilePath;
    }

    public Path getGradeBookFilePath() {
        return gradeBookFilePath;
    }

    public void setTutorBookFilePath(Path tutorBookFilePath) {
        requireNonNull(tutorBookFilePath);
        this.tutorBookFilePath = tutorBookFilePath;
    }

    public Path getScheduleTrackerFilePath() {
        return scheduleTrackerFilePath;
    }

    public Path getReminderTrackerFilePath() {
        return reminderTrackerFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        requireNonNull(appointmentBookFilePath);
        this.appointmentBookFilePath = appointmentBookFilePath;
    }

    public void setGradeBookFilePath(Path gradeBookFilePath) {
        requireNonNull(gradeBookFilePath);
        this.gradeBookFilePath = gradeBookFilePath;
    }

    public void setScheduleTrackerFilePath(Path scheduleTrackerFilePath) {
        requireNonNull(scheduleTrackerFilePath);
        this.scheduleTrackerFilePath = scheduleTrackerFilePath;
    }

    public void setReminderTrackerFilePath(Path reminderTrackerFilePath) {
        requireNonNull(reminderTrackerFilePath);
        this.reminderTrackerFilePath = reminderTrackerFilePath;
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
                && tutorBookFilePath.equals(o.tutorBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, tutorBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + tutorBookFilePath);
        return sb.toString();
    }

}
