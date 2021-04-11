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
    private Path personBookFilePath = Paths.get("data" , "addressbook.json");
    private Path dishBookFilePath = Paths.get("data", "dishbook.json");
    private Path ingredientBookFilePath = Paths.get("data", "ingredientbook.json");
    private Path orderBookFilePath = Paths.get("data", "orderbook.json");

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
        setPersonBookFilePath(newUserPrefs.getPersonBookFilePath());
        setDishBookFilePath(newUserPrefs.getDishBookFilePath());
        setIngredientBookFilePath(newUserPrefs.getIngredientBookFilePath());
        setOrderBookFilePath(newUserPrefs.getOrderBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPersonBookFilePath() {
        return personBookFilePath;
    }

    public void setPersonBookFilePath(Path personBookFilePath) {
        requireNonNull(personBookFilePath);
        this.personBookFilePath = personBookFilePath;
    }

    public Path getDishBookFilePath() {
        return dishBookFilePath;
    }

    public void setDishBookFilePath(Path dishBookFilePath) {
        requireNonNull(dishBookFilePath);
        this.dishBookFilePath = dishBookFilePath;
    }

    public Path getIngredientBookFilePath() {
        return ingredientBookFilePath;
    }

    public void setIngredientBookFilePath(Path ingredientBookFilePath) {
        requireNonNull(ingredientBookFilePath);
        this.ingredientBookFilePath = ingredientBookFilePath;
    }

    public Path getOrderBookFilePath() {
        return orderBookFilePath;
    }

    public void setOrderBookFilePath(Path orderBookFilePath) {
        requireNonNull(orderBookFilePath);
        this.orderBookFilePath = orderBookFilePath;
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
                && personBookFilePath.equals(o.personBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, personBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + personBookFilePath);
        return sb.toString();
    }

}
