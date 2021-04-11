package seedu.address.storage.person;

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
import seedu.address.model.person.Person;
import seedu.address.storage.BookStorage;

/**
 * A class to access PersonBook data stored as a json file on the hard disk.
 */
public class JsonPersonBookStorage implements BookStorage<Person> {

    private static final Logger logger = LogsCenter.getLogger(JsonPersonBookStorage.class);

    private Path filePath;

    public JsonPersonBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBook<Person>> readBook() throws DataConversionException {
        return readBook(filePath);
    }

    @Override
    public Optional<ReadOnlyBook<Person>> readBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePersonBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePersonBook.class);
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
    public void saveBook(ReadOnlyBook<Person> personBook) throws IOException {
        saveBook(personBook, filePath);
    }

    @Override
    public void saveBook(ReadOnlyBook<Person> personBook, Path filePath) throws IOException {
        requireNonNull(personBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePersonBook(personBook), filePath);
    }

}
