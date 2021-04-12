package seedu.address.storage.dish;

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
import seedu.address.model.dish.Dish;
import seedu.address.storage.BookStorage;
import seedu.address.storage.person.JsonPersonBookStorage;

/**
 * A class to access DishBook data stored as a json file on the hard disk.
 */
public class JsonDishBookStorage implements BookStorage<Dish> {

    private static final Logger logger = LogsCenter.getLogger(JsonPersonBookStorage.class);

    private Path filePath;

    public JsonDishBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBook<Dish>> readBook() throws DataConversionException, IOException {
        return readBook(filePath);
    }

    @Override
    public Optional<ReadOnlyBook<Dish>> readBook(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableDishBook> jsonDishBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableDishBook.class);
        if (!jsonDishBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDishBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBook(ReadOnlyBook<Dish> dishBook) throws IOException {
        saveBook(dishBook, filePath);
    }

    @Override
    public void saveBook(ReadOnlyBook<Dish> book, Path filePath) throws IOException {
        requireNonNull(book);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDishBook(book), filePath);
    }

}
