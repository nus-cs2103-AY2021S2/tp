package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.food.FoodIntakeList;

/**
 * A class to access FoodIntakeList data stored as a json file on the hard disk.
 */
public class JsonFoodIntakeListStorage implements FoodIntakeListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniqueFoodListStorage.class);

    private Path filePath;

    public JsonFoodIntakeListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getFoodIntakeListFilePath() {
        return filePath;
    }

    @Override
    public Optional<FoodIntakeList> readFoodIntakeList() throws DataConversionException, IOException {
        return readFoodIntakeList(filePath);
    }

    /**
     * Similar to {@link #readFoodIntakeList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<FoodIntakeList> readFoodIntakeList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFoodIntakeList> jsonFoodIntakeList = JsonUtil.readJsonFile(
                filePath, JsonSerializableFoodIntakeList.class);
        if (!jsonFoodIntakeList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFoodIntakeList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFoodIntakeList(FoodIntakeList foodIntakeList) throws IOException {
        saveFoodIntakeList(foodIntakeList, filePath);
    }

    /**
     * Similar to {@link #saveFoodIntakeList(FoodIntakeList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFoodIntakeList(FoodIntakeList foodIntakeList, Path filePath) throws IOException {
        requireNonNull(foodIntakeList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFoodIntakeList(foodIntakeList), filePath);
    }

}
