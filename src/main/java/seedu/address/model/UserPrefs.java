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
    private Path friendDexFilePath = Paths.get("data" , "frienddex.json");
    private Path pictureStorageDirPath = Paths.get("data");

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
        setPictureStorageDirPath(newUserPrefs.getPictureStorageDirPath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return friendDexFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.friendDexFilePath = addressBookFilePath;
    }

    public Path getPictureStorageDirPath() {
        return pictureStorageDirPath;
    }

    public void setPictureStorageDirPath(Path pictureStorageDirPath) {
        requireNonNull(pictureStorageDirPath);
        this.pictureStorageDirPath = pictureStorageDirPath;
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
                && friendDexFilePath.equals(o.friendDexFilePath)
                && pictureStorageDirPath.equals(o.pictureStorageDirPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, friendDexFilePath, pictureStorageDirPath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + friendDexFilePath);
        sb.append("\nLocal picture storage dir location: " + pictureStorageDirPath);
        return sb.toString();
    }

}
