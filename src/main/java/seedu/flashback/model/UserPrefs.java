package seedu.flashback.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.flashback.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path flashBackFilePath = Paths.get("data" , "flashback.json");
    private AliasMap aliasMap = new AliasMap();

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
        setFlashBackFilePath(newUserPrefs.getFlashBackFilePath());
        setAliasMap(newUserPrefs.getAliasMap());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFlashBackFilePath() {
        return flashBackFilePath;
    }

    public void setFlashBackFilePath(Path flashBackFilePath) {
        requireNonNull(flashBackFilePath);
        this.flashBackFilePath = flashBackFilePath;
    }

    public AliasMap getAliasMap() {
        return aliasMap;
    }

    public void setAliasMap(AliasMap aliasMap) {
        requireNonNull(aliasMap);
        this.aliasMap = aliasMap;
    }

    public void addAlias(String command, String name) {
        aliasMap.addAlias(command, name);
    }

    public boolean canAddAlias(String command, String name) {
        return aliasMap.canAddAlias(command, name);
    }

    public String parseAlias(String input) {
        return aliasMap.parseAlias(input);
    }

    public boolean isAlias(String input) {
        return aliasMap.isAlias(input);
    }

    public boolean isCommand(String input) {
        return aliasMap.isCommand(input);
    }

    public boolean isReview(String input) {
        return aliasMap.isReview(input);
    }

    public boolean isAliasMapCorrupted() {
        return aliasMap.isCorrupted();
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
                && flashBackFilePath.equals(o.flashBackFilePath)
                && aliasMap.equals(o.aliasMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, flashBackFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + flashBackFilePath);
        return sb.toString();
    }

}
