package fooddiary.storage;

import fooddiary.commons.core.LogsCenter;
import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.ReadOnlyUserPrefs;
import fooddiary.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of FoodDiary data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FoodDiaryStorage foodDiaryStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FoodDiaryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FoodDiaryStorage foodDiaryStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.foodDiaryStorage = foodDiaryStorage;
        this.userPrefsStorage = userPrefsStorage;
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


    // ================ FoodDiary methods ==============================

    @Override
    public Path getFoodDiaryFilePath() {
        return foodDiaryStorage.getFoodDiaryFilePath();
    }

    @Override
    public Optional<ReadOnlyFoodDiary> readFoodDiary() throws DataConversionException, IOException {
        return readFoodDiary(foodDiaryStorage.getFoodDiaryFilePath());
    }

    @Override
    public Optional<ReadOnlyFoodDiary> readFoodDiary(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return foodDiaryStorage.readFoodDiary(filePath);
    }

    @Override
    public void saveFoodDiary(ReadOnlyFoodDiary foodDiary) throws IOException {
        saveFoodDiary(foodDiary, foodDiaryStorage.getFoodDiaryFilePath());
    }

    @Override
    public void saveFoodDiary(ReadOnlyFoodDiary foodDiary, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        foodDiaryStorage.saveFoodDiary(foodDiary, filePath);
    }

}
