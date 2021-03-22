package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.User;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);

    private AddressBookStorage addressBookStorage;
    private UniqueFoodListStorage uniqueFoodListStorage;
    private FoodIntakeListStorage foodIntakeListStorage;
    private DietPlanListStorage dietPlanListStorage;
    private UserPrefsStorage userPrefsStorage;
    private UserStorage userStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code UniqueFoodListStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UniqueFoodListStorage uniqueFoodListStorage,
                          FoodIntakeListStorage foodIntakeListStorage, DietPlanListStorage dietPlanListStorage,
                          UserPrefsStorage userPrefsStorage, UserStorage userStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.uniqueFoodListStorage = uniqueFoodListStorage;
        this.foodIntakeListStorage = foodIntakeListStorage;
        this.dietPlanListStorage = dietPlanListStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.userStorage = userStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ UniqueFoodList methods ==============================

    @Override
    public Path getFoodListFilePath() {
        return uniqueFoodListStorage.getFoodListFilePath();
    }

    @Override
    public Optional<UniqueFoodList> readFoodList() throws DataConversionException, IOException {
        return readFoodList(uniqueFoodListStorage.getFoodListFilePath());
    }

    @Override
    public Optional<UniqueFoodList> readFoodList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return uniqueFoodListStorage.readFoodList(filePath);
    }

    @Override
    public void saveFoodList(UniqueFoodList foodList) throws IOException {
        saveFoodList(foodList, uniqueFoodListStorage.getFoodListFilePath());
    }

    @Override
    public void saveFoodList(UniqueFoodList foodList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        uniqueFoodListStorage.saveFoodList(foodList, filePath);
    }

    // ================ FoodIntakeList methods ==============================

    @Override
    public Path getFoodIntakeListFilePath() {
        return foodIntakeListStorage.getFoodIntakeListFilePath();
    }

    @Override
    public Optional<FoodIntakeList> readFoodIntakeList() throws DataConversionException, IOException {
        return readFoodIntakeList(foodIntakeListStorage.getFoodIntakeListFilePath());
    }

    @Override
    public Optional<FoodIntakeList> readFoodIntakeList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return foodIntakeListStorage.readFoodIntakeList(filePath);
    }

    @Override
    public void saveFoodIntakeList(FoodIntakeList foodIntakeList) throws IOException {
        saveFoodIntakeList(foodIntakeList, foodIntakeListStorage.getFoodIntakeListFilePath());
    }

    @Override
    public void saveFoodIntakeList(FoodIntakeList foodIntakeList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        foodIntakeListStorage.saveFoodIntakeList(foodIntakeList, filePath);
    }

    // ================ DietPlanList methods ==============================

    @Override
    public Path getDietPlanListFilePath() {
        return dietPlanListStorage.getDietPlanListFilePath();
    }

    @Override
    public Optional<DietPlanList> readDietPlanList() throws DataConversionException, IOException {
        return readDietPlanList(dietPlanListStorage.getDietPlanListFilePath());
    }

    @Override
    public Optional<DietPlanList> readDietPlanList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return dietPlanListStorage.readDietPlanList();
    }

    @Override
    public void saveDietPlanList(DietPlanList dietPlanList) throws IOException {
        saveDietPlanList(dietPlanList, dietPlanListStorage.getDietPlanListFilePath());
    }

    @Override
    public void saveDietPlanList(DietPlanList dietPlanList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        dietPlanListStorage.saveDietPlanList(dietPlanList, filePath);
    }

    // ================ User methods ==============================
    @Override
    public Path getUserFilePath() {
        return userStorage.getUserFilePath();
    }

    @Override
    public Optional<User> readUser() throws DataConversionException, IOException {
        return readUser(userStorage.getUserFilePath());
    }

    @Override
    public Optional<User> readUser(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return userStorage.readUser();
    }

    @Override
    public void saveUser(User user) throws IOException {
        saveUser(user, userStorage.getUserFilePath());
    }

    @Override
    public void saveUser(User user, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        userStorage.saveUser(user, filePath);
    }
}
