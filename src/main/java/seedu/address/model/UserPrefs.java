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
    private Path taskListFilePath = Paths.get("data", "taskList.json");
    private Path eventListFilePath = Paths.get("data", "eventList.json");
    private Path socheduleFilePath = Paths.get("data", "sochedule.json");

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
        setTaskListFilePath(newUserPrefs.getTaskListFilePath());
        setEventListFilePath(newUserPrefs.getEventListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getTaskListFilePath() {
        return taskListFilePath;
    }

    public Path getEventListFilePath() {
        return eventListFilePath;
    }

    public Path getSocheduleFilePath() {
        return socheduleFilePath;
    }

    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        this.taskListFilePath = taskListFilePath;
    }

    public void setEventListFilePath(Path eventListFilePath) {
        requireNonNull(eventListFilePath);
        this.eventListFilePath = eventListFilePath;
    }

    public void setSocheduleFilePath(Path socheduleFilePath) {
        requireNonNull(socheduleFilePath);
        this.socheduleFilePath = socheduleFilePath;
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
                && taskListFilePath.equals(o.taskListFilePath)
                && eventListFilePath.equals(o.eventListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, taskListFilePath, eventListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal Task List file location : " + taskListFilePath);
        sb.append("\nLocal Event List file location : " + eventListFilePath);
        return sb.toString();
    }

}
