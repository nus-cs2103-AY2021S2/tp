package seedu.address.storage.order;

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
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.order.Order;
import seedu.address.storage.BookStorage;
import seedu.address.storage.person.JsonPersonBookStorage;

/**
 * A class to access OrderBook data stored as a json file on the hard disk.
 */
public class JsonOrderBookStorage implements BookStorage<Order> {

    private static final Logger logger = LogsCenter.getLogger(JsonPersonBookStorage.class);

    private Path filePath;

    public JsonOrderBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBook<Order>> readBook() throws DataConversionException, IOException {
        return readBook(filePath);
    }

    @Override
    public Optional<ReadOnlyBook<Order>> readBook(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableOrderBook> jsonOrderBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableOrderBook.class);
        if (!jsonOrderBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonOrderBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBook(ReadOnlyBook<Order> orderBook) throws IOException {
        saveBook(orderBook, filePath);
    }

    @Override
    public void saveBook(ReadOnlyBook<Order> orderBook, Path filePath) throws IOException {
        requireNonNull(orderBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableOrderBook(orderBook), filePath);
    }

}
