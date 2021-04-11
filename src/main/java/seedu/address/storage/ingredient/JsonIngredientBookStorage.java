package seedu.address.storage.ingredient;

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
import seedu.address.model.ingredient.Ingredient;
import seedu.address.storage.BookStorage;
import seedu.address.storage.person.JsonPersonBookStorage;

/**
 * A class to access IngredientBook data stored as a json file on the hard disk.
 */
public class JsonIngredientBookStorage implements BookStorage<Ingredient> {

    private static final Logger logger = LogsCenter.getLogger(JsonPersonBookStorage.class);

    private Path filePath;

    public JsonIngredientBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBook<Ingredient>> readBook() throws DataConversionException, IOException {
        return readBook(filePath);
    }

    @Override
    public Optional<ReadOnlyBook<Ingredient>> readBook(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableIngredientBook> jsonIngredientBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableIngredientBook.class);
        if (!jsonIngredientBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonIngredientBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBook(ReadOnlyBook<Ingredient> ingredientBook) throws IOException {
        saveBook(ingredientBook, filePath);
    }

    @Override
    public void saveBook(ReadOnlyBook<Ingredient> ingredientBook, Path filePath) throws IOException {
        requireNonNull(ingredientBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableIngredientBook(ingredientBook), filePath);
    }

}
