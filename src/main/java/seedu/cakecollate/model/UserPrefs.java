package seedu.cakecollate.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.cakecollate.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path cakeCollateFilePath = Paths.get("data" , "cakecollate.json");
    private Path orderItemsFilePath = Paths.get("data", "OrderItems.json");
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
        setCakeCollateFilePath(newUserPrefs.getCakeCollateFilePath());
        setOrderItemsFilePath(newUserPrefs.getOrderItemsFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getCakeCollateFilePath() {
        return cakeCollateFilePath;
    }

    public void setCakeCollateFilePath(Path cakeCollateFilePath) {
        requireNonNull(cakeCollateFilePath);
        this.cakeCollateFilePath = cakeCollateFilePath;
    }

    public Path getOrderItemsFilePath() {
        return orderItemsFilePath;
    }

    public void setOrderItemsFilePath(Path orderItemsFilePath) {
        requireNonNull(orderItemsFilePath);
        this.orderItemsFilePath = orderItemsFilePath;
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
                && cakeCollateFilePath.equals(o.cakeCollateFilePath)
                && orderItemsFilePath.equals(o.orderItemsFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, cakeCollateFilePath, orderItemsFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + cakeCollateFilePath);
        return sb.toString();
    }

}
