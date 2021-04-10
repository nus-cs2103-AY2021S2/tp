package seedu.timeforwheels.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.timeforwheels.commons.core.LogsCenter;
import seedu.timeforwheels.commons.exceptions.DataConversionException;
import seedu.timeforwheels.commons.exceptions.IllegalValueException;
import seedu.timeforwheels.commons.util.FileUtil;
import seedu.timeforwheels.commons.util.JsonUtil;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;

/**
 * A class to access DeliveryList data stored as a json file on the hard disk.
 */
public class JsonDeliveryListStorage implements DeliveryListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDeliveryListStorage.class);

    private Path filePath;

    public JsonDeliveryListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDeliveryListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDeliveryList> readDeliveryList() throws DataConversionException {
        return readDeliveryList(filePath);
    }

    /**
     * Similar to {@link #readDeliveryList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDeliveryList> readDeliveryList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDeliveryList> jsonDeliveryList = JsonUtil.readJsonFile(
                filePath, JsonSerializableDeliveryList.class);
        if (!jsonDeliveryList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDeliveryList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDeliveryList(ReadOnlyDeliveryList deliveryList) throws IOException {
        saveDeliveryList(deliveryList, filePath);
    }

    /**
     * Similar to {@link #saveDeliveryList(ReadOnlyDeliveryList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDeliveryList(ReadOnlyDeliveryList deliveryList, Path filePath) throws IOException {
        requireNonNull(deliveryList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDeliveryList(deliveryList), filePath);
    }

}
