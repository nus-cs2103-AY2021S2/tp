package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.person.Person;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.User;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final UniqueFoodList uniqueFoodList;
    private final FoodIntakeList foodIntakeList;
    private final DietPlanList dietPlanList;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UniqueFoodList uniqueFoodList, FoodIntakeList foodIntakeList,
                        DietPlanList dietPlanList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        this.addressBook = new AddressBook(addressBook, uniqueFoodList, foodIntakeList);
        this.uniqueFoodList = uniqueFoodList;
        this.foodIntakeList = foodIntakeList;
        this.dietPlanList = dietPlanList;
        this.userPrefs = new UserPrefs(userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", unique food list: " + uniqueFoodList + ", food intake list: " + foodIntakeList
                + ", diet plan list: " + dietPlanList + " and user prefs " + userPrefs);

        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    /**
     * Initializes a ModelManager.
     */
    public ModelManager() {
        this(new AddressBook(), new UniqueFoodList(),
                new FoodIntakeList(), new DietPlanList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && uniqueFoodList.equals(other.uniqueFoodList)
                && foodIntakeList.equals(other.foodIntakeList)
                && filteredPersons.equals(other.filteredPersons);
    }

    //=========== UniqueFoodList Accessors =============================================================

    @Override
    public UniqueFoodList getUniqueFoodList() {
        return uniqueFoodList;
    }

    @Override
    public boolean hasFoodItem(Food food) {
        requireNonNull(food);
        return addressBook.hasFoodItem(food);
    }

    @Override
    public void addFoodItem(Food food) {
        addressBook.addFoodItem(food);
    }

    @Override
    public void updateFoodItem(Food food) {
        addressBook.updateFoodItem(food);
    }

    @Override
    public void deleteFoodItem(int index) {
        addressBook.deleteFoodItem(index);
    }

    @Override
    public String listFoodItem() {
        return addressBook.listFoodItem();
    }

    @Override
    public void addUser(User user) {
        addressBook.addUser(user);
    }

    @Override
    public boolean hasUser() {
        return addressBook.hasUser();
    }

    @Override
    public void editUser(User updateUser) {
        User oldUser = addressBook.getUser();
        User newUser = new User(updateUser.getBmi(), oldUser.getFoodList(),
                oldUser.getFoodIntakeList(), updateUser.getAge(),
                updateUser.getGender(), updateUser.getIdealWeight());
        addressBook.addUser(newUser);
    }

    @Override
    public String listUser() {
        User user = addressBook.getUser();
        Bmi bmi = user.getBmi();
        String details = "Below is your current height and weight:\nLast Updated: "
                + user.getLastUpdated() + "\nWeight: " + bmi.getWeight()
                + " kg\nHeight: " + bmi.getHeight() + " cm\n BMI: ";

        if (bmi.getWeight() <= bmi.getLowerBoundWeight()) {
            details += String.format("%.2f", bmi.calculateBmi()) + " (High Risk of nutritional deficiency)";
        } else if (bmi.getWeight() >= bmi.getUpperBoundWeight()) {
            details += String.format("%.2f", bmi.calculateBmi()) + " (High Risk of Obesity-related diseases)";
        } else {
            details += String.format("%.2f", bmi.calculateBmi()) + " (Healthy range)";
        }

        return details;
    }

    @Override
    public User getUser() {
        return addressBook.getUser();
    }

    @Override
    public PlanType getUserGoal() {
        User user = addressBook.getUser();
        Bmi bmi = user.getBmi();

        if (bmi.getWeight() <= bmi.getLowerBoundWeight()) {
            return PlanType.WEIGHTGAIN;
        } else if (bmi.getWeight() >= bmi.getUpperBoundWeight()) {
            return PlanType.WEIGHTLOSS;
        } else {
            return PlanType.WEIGHTMAINTAIN;
        }

    }

    @Override
    public double getUserBmi() {
        User user = addressBook.getUser();
        Bmi bmi = user.getBmi();

        return bmi.getBmi();
    }

    //=========== FoodIntakeList Accessors =============================================================

    @Override
    public void addFoodIntake(LocalDate date, Food food) {
        addressBook.addFoodIntake(date, food);
    }

    @Override
    public void updateFoodIntake(int index, FoodIntake foodIntake) {
        addressBook.updateFoodIntake(index, foodIntake);
    }

    @Override
    public FoodIntakeList getFoodIntakeList() {
        return foodIntakeList;
    }

    //=========== DietPlanList Accessors =============================================================

    @Override
    public DietPlanList getDietPlanList() {
        return dietPlanList;
    }

    @Override
    public void setActiveDiet(DietPlan dietPlan) {
        User user = addressBook.getUser();
        user.setActiveDietPlan(dietPlan);
    }

    @Override
    public DietPlan getActiveDiet() {
        User user = addressBook.getUser();
        return user.getActiveDietPlan();
    }

    @Override
    public List<DietPlan> recommendDiets(PlanType planType) {
        List<DietPlan> recommendedDiets = new ArrayList<>();
        Iterator<DietPlan> iterator = dietPlanList.iterator();
        while (iterator.hasNext()) {
            DietPlan dietPlan = iterator.next();
            if (dietPlan.getPlanType() == planType) {
                recommendedDiets.add(dietPlan);
            }
        }
        return recommendedDiets;
    }

}
