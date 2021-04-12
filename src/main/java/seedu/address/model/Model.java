package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.User;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' diet lah file path.
     */
    Path getDietLahFilePath();

    /**
     * Sets the user prefs' diet lah file path.
     */
    void setDietLahFilePath(Path dietLahFilePath);

    /**
     * Replaces diet lah data with the data in {@code dietLah}.
     */
    void setDietLah(ReadOnlyDietLah dietLah);

    /** Returns the DietLah */
    ReadOnlyDietLah getDietLah();

    //=========== UniqueFoodList Accessors =============================================================

    /**
     * Returns true if a food with the same name as {@code food} exists in the food list.
     */
    boolean hasFoodItem(Food food);

    /**
     * Lists all food items from the food items list.
     *
     * @return string output of all the food items
     */
    String listFoodItem();

    /**
     * Adds the given food item.
     * {@code food} must not already exist in the food list.
     */
    void addFoodItem(Food food);

    /**
     * Updates food in food item list.
     * @param food updated food item
     */
    void updateFoodItem(Food food);

    /**
     * Deletes food in food item list.
     * @param index food index to be deleted
     */
    void deleteFoodItem(int index);

    /** Returns the UniqueFoodList */
    UniqueFoodList getUniqueFoodList();

    //=========== DietPlanList Accessors ==============================================================

    /** Returns the list of diet plans */
    DietPlanList getDietPlanList();

    /** Set active diet plan */
    void setActiveDiet(DietPlan dietPlan);

    /** Get active diet plan */
    DietPlan getActiveDiet();

    /** Recommend diet plan based on user's goals*/
    HashMap<Integer, DietPlan> recommendDiets(PlanType planType);

    //=========== FoodIntakeList Accessors ==============================================================

    /**
     * Adds the Food consumed on the specified day to the food intake list.
     * @param date date of intake
     * @param food food object
     *
     * @return Food that was successfully added to FoodIntakeList
     */
    Food addFoodIntake(LocalDate date, Food food);

    /**
     * Updates the FoodIntake object in the FoodIntakeList
     *
     * @param index index to replace
     * @param foodIntake FoodIntake object to replace
     */
    void updateFoodIntake(int index, FoodIntake foodIntake);

    /**
     * Gets Food intake list.
     *
     * @return food intake list
     */
    FoodIntakeList getFoodIntakeList();

    //=========== User Accessors ==============================================================

    /**
     * Adds the given user item.
     */
    void addUser(User user);

    /**
     * Checks whether user has been initialized
     */
    boolean hasUser();

    /**
     * Updates the user object.
     */
    void editUser(User user);

    /**
     * Lists the user object.
     */
    String listUser();

    /**
     * Calculate user goal
     */
    PlanType getUserGoal();

    /**
     * Get user BMI
     */
    double getUserBmi();

    /**
     * Get user object
     */
    User getUser();

    /**
     * Reset DietLah to template
     */
    void resetToTemplate();

    /**
     * Reset DietLah to blank state
     */
    void resetToBlank();

    /**
     * Check if this instance and supplied modelManager is equal
     */
    boolean equals(Object object);

}
