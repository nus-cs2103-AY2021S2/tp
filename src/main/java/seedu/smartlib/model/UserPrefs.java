package seedu.smartlib.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.smartlib.commons.core.GuiSettings;

/**
 * Represents the User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path smartLibFilePath = Paths.get("data" , "smartlib.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     *
     * @param userPrefs the user's preferences.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     *
     * @param newUserPrefs a blank copy of userPrefs.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setSmartLibFilePath(newUserPrefs.getSmartLibFilePath());
    }

    /**
     * Returns the GUI settings of the userPrefs.
     *
     * @return the GUI settings of the userPrefs.
     */
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    /**
     * Updates the GUI settings of the userPrefs.
     *
     * @param guiSettings the new GUI settings.
     */
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    /**
     * Returns the file path to SmartLib registered in the userPrefs.
     *
     * @return the file path to SmartLib registered in the userPrefs.
     */
    public Path getSmartLibFilePath() {
        return smartLibFilePath;
    }

    /**
     * Updates the file path to SmartLib registered in the userPrefs.
     *
     * @param smartLibFilePath the new file path to SmartLib.
     */
    public void setSmartLibFilePath(Path smartLibFilePath) {
        requireNonNull(smartLibFilePath);
        this.smartLibFilePath = smartLibFilePath;
    }

    /**
     * Checks if this UserPrefs object is equal to another UserPrefs object.
     *
     * @param other the other UserPrefs object to be compared.
     * @return true if this UserPrefs object is equal to the other UserPrefs object, and false otherwise.
     */
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
                && smartLibFilePath.equals(o.smartLibFilePath);
    }

    /**
     * Generates a hashcode for this UserPrefs object.
     *
     * @return the hashcode for this UserPrefs object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, smartLibFilePath);
    }

    /**
     * Returns this UserPrefs object in String format.
     *
     * @return this UserPrefs object in String format.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + smartLibFilePath);
        return sb.toString();
    }

}
