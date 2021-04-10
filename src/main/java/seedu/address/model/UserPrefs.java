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
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path datesBookFilePath = Paths.get("data", "datesbook.json");
    private Path lessonBookFilePath = Paths.get("data", "lessonbook.json");

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
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setDatesBookFilePath(newUserPrefs.getDatesBookFilePath());
        setLessonBookFilePath(newUserPrefs.getLessonBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getDatesBookFilePath() {
        return datesBookFilePath;
    }

    public void setDatesBookFilePath(Path datesBookFilePath) {
        requireNonNull(datesBookFilePath);
        this.datesBookFilePath = datesBookFilePath;
    }

    public Path getLessonBookFilePath() {
        return lessonBookFilePath;
    }

    public void setLessonBookFilePath(Path lessonBookFilePath) {
        requireNonNull(lessonBookFilePath);
        this.lessonBookFilePath = lessonBookFilePath;
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
                && addressBookFilePath.equals(o.addressBookFilePath)
                && datesBookFilePath.equals(o.datesBookFilePath)
                && lessonBookFilePath.equals(o.lessonBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, datesBookFilePath, lessonBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location for contacts: " + addressBookFilePath);
        sb.append("\nLocal data file location for dates: " + datesBookFilePath);
        sb.append("\nLocal data file location for lessons: " + lessonBookFilePath);
        return sb.toString();
    }

}
