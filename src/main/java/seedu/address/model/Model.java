package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.person.Person;
import seedu.address.model.user.User;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    //=========== UnqiueFoodList Accessors =============================================================
    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

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
    List<DietPlan> recommendDiets(PlanType planType);

    //=========== FoodIntakeList Accessors ==============================================================

    /**
     * Adds the food consumed on the day to the food intake list.
     * @param date date of intake
     * @param food food object
     */
    void addFoodIntake(LocalDate date, Food food);

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
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

}
