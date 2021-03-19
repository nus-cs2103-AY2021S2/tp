package fooddiary.storage;

import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.ReadOnlyUserPrefs;
import fooddiary.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends FoodDiaryStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFoodDiaryFilePath();

    @Override
    Optional<ReadOnlyFoodDiary> readFoodDiary() throws DataConversionException, IOException;

    @Override
    void saveFoodDiary(ReadOnlyFoodDiary foodDiary) throws IOException;

}
