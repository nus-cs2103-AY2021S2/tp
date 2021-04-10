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
    private Path dietLahFilePath = Paths.get("data" , "dietLah.json");
    private Path uniqueFoodListFilePath = Paths.get("data" , "foodlist.json");
    private Path foodIntakeListFilePath = Paths.get("data" , "foodintakelist.json");
    private Path dietPlanListFilePath = Paths.get("data" , "dietplanlist.json");
    private Path userFilePath = Paths.get("data", "user.json");

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
        setDietLahFilePath(newUserPrefs.getDietLahFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getDietLahFilePath() {
        return dietLahFilePath;
    }

    public Path getUniqueFoodListFilePath() {
        return uniqueFoodListFilePath;
    }

    public Path getFoodIntakeListFilePath() {
        return foodIntakeListFilePath;
    }

    public Path getDietPlanListFilePath() {
        return dietPlanListFilePath;
    }

    public Path getUserFilePath() {
        return userFilePath;
    }

    public void setDietLahFilePath(Path dietLahFilePath) {
        requireNonNull(dietLahFilePath);
        this.dietLahFilePath = dietLahFilePath;
    }

    public void setUniqueFoodListFilePath(Path uniqueFoodListFilePath) {
        requireNonNull(uniqueFoodListFilePath);
        this.uniqueFoodListFilePath = uniqueFoodListFilePath;
    }

    public void setFoodIntakeListFilePath(Path foodIntakeListFilePath) {
        requireNonNull(foodIntakeListFilePath);
        this.foodIntakeListFilePath = foodIntakeListFilePath;
    }

    public void setDietPlanListFilePath(Path dietPlanListFilePath) {
        requireNonNull(dietPlanListFilePath);
        this.dietPlanListFilePath = dietPlanListFilePath;
    }

    public void setUserFilePath(Path userFilePath) {
        requireNonNull(userFilePath);
        this.userFilePath = userFilePath;
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
                && dietLahFilePath.equals(o.dietLahFilePath)
                && uniqueFoodListFilePath.equals(o.uniqueFoodListFilePath)
                && foodIntakeListFilePath.equals(o.foodIntakeListFilePath)
                && dietPlanListFilePath.equals(o.dietPlanListFilePath)
                && userFilePath.equals(o.userFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, dietLahFilePath, uniqueFoodListFilePath,
                foodIntakeListFilePath, dietPlanListFilePath, userFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + dietLahFilePath);
        sb.append("\nLocal unique food list file location : " + uniqueFoodListFilePath);
        sb.append("\nLocal data file location : " + foodIntakeListFilePath);
        sb.append("\nLocal diet plan list file location : " + dietPlanListFilePath);
        sb.append("\nUser file location : " + userFilePath);
        return sb.toString();
    }

}
