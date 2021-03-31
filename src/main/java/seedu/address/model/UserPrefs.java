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
    private Path propertyBookFilePath = Paths.get("data", "propertybook.json");
    private Path appointmentBookFilePath = Paths.get("data", "appointmentbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

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
        setAppointmentBookFilePath(newUserPrefs.getAppointmentBookFilePath());
        setPropertyBookFilePath(newUserPrefs.getPropertyBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPropertyBookFilePath() {
        return propertyBookFilePath;
    }

    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        this.propertyBookFilePath = propertyBookFilePath;
    }

    public Path getAppointmentBookFilePath() {
        return appointmentBookFilePath;
    }

    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        requireNonNull(appointmentBookFilePath);
        this.appointmentBookFilePath = appointmentBookFilePath;
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
                && propertyBookFilePath.equals(o.propertyBookFilePath)
                && appointmentBookFilePath.equals(o.appointmentBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, propertyBookFilePath, appointmentBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal property data file location : " + propertyBookFilePath);
        sb.append("\nLocal appointment data file location : " + appointmentBookFilePath);
        return sb.toString();
    }

}
