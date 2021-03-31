package seedu.cakecollate.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.commons.exceptions.DataConversionException;
import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.commons.util.FileUtil;
import seedu.cakecollate.commons.util.JsonUtil;
import seedu.cakecollate.model.ReadOnlyOrderItems;

/**
 * A class to access OrderItems data stored as a json file on the hard disk.
 */
public class JsonOrderItemsStorage implements OrderItemsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonOrderItemsStorage.class);

    private Path filePath;

    public JsonOrderItemsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getOrderItemsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyOrderItems> readOrderItems() throws DataConversionException {
        return readOrderItems(filePath);
    }

    /**
     * Similar to {@link #readOrderItems()}
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyOrderItems> readOrderItems(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableOrderItems> jsonOrderItem = JsonUtil.readJsonFile(filePath,
                JsonSerializableOrderItems.class);
        if (!jsonOrderItem.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonOrderItem.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveOrderItems(ReadOnlyOrderItems orderItems) throws IOException {
        saveOrderItems(orderItems, filePath);
    }

    /**
     * Similar to {@link #saveOrderItems(ReadOnlyOrderItems)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveOrderItems(ReadOnlyOrderItems orderItems, Path filePath) throws IOException {
        requireNonNull(orderItems);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableOrderItems(orderItems), filePath);
    }

}
