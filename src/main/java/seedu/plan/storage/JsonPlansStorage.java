package seedu.plan.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.plan.commons.core.LogsCenter;
import seedu.plan.commons.exceptions.DataConversionException;
import seedu.plan.commons.exceptions.IllegalValueException;
import seedu.plan.commons.util.FileUtil;
import seedu.plan.commons.util.JsonUtil;
import seedu.plan.model.ReadOnlyModulePlanner;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonPlansStorage implements ModulePlannerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPlansStorage.class);

    private Path filePath;

    public JsonPlansStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPlansFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModulePlanner> readPlans() throws DataConversionException {
        return readPlans(filePath);
    }

    /**
     * Similar to {@link #readPlans()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyModulePlanner> readPlans(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePlans> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePlans.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePlans(ReadOnlyModulePlanner plans) throws IOException {
        savePlans(plans, filePath);
    }

    /**
     * Similar to {@link #savePlans(ReadOnlyModulePlanner)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePlans(ReadOnlyModulePlanner plans, Path filePath) throws IOException {
        requireNonNull(plans);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePlans(plans), filePath);
    }
}
