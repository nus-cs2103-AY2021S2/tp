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
import seedu.address.model.diet.DietPlanList;

/**
 * A class to access DietPlanList data stored as a json file on the hard disk.
 */
public class JsonDietPlanListStorage implements DietPlanListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDietPlanListStorage.class);

    private Path filePath;

    public JsonDietPlanListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getDietPlanListFilePath() {
        return filePath;
    }

    @Override
    public Optional<DietPlanList> readDietPlanList() throws DataConversionException, IOException {
        return readDietPlanList(filePath);
    }

    /**
     * Similar to {@link #readDietPlanList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<DietPlanList> readDietPlanList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDietPlanList> jsonPlanList = JsonUtil.readJsonFile(
                filePath, JsonSerializableDietPlanList.class);
        if (!jsonPlanList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPlanList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDietPlanList(DietPlanList dietPlanList) throws IOException {
        saveDietPlanList(dietPlanList, filePath);
    }

    /**
     * Similar to {@link #saveDietPlanList(DietPlanList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDietPlanList(DietPlanList dietPlanList, Path filePath) throws IOException {
        requireNonNull(dietPlanList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDietPlanList(dietPlanList), filePath);
    }

}
