package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UniqueFoodListStorage,
        FoodIntakeListStorage, DietPlanListStorage, UserStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /*@Override
    Path getDietLahFilePath();

    @Override
    Optional<ReadOnlyDietLah> readDietLah() throws DataConversionException, IOException;

    @Override
    void saveDietLah(ReadOnlyDietLah dietLah) throws IOException;
*/
}
