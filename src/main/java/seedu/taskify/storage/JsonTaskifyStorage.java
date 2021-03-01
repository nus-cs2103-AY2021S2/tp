package seedu.taskify.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taskify.commons.core.LogsCenter;
import seedu.taskify.commons.exceptions.DataConversionException;
import seedu.taskify.commons.exceptions.IllegalValueException;
import seedu.taskify.commons.util.FileUtil;
import seedu.taskify.commons.util.JsonUtil;
import seedu.taskify.model.ReadOnlyTaskify;

/**
 * A class to access TaskifyParser data stored as a json file on the hard disk.
 */
public class JsonTaskifyStorage implements TaskifyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskifyStorage.class);

    private Path filePath;

    public JsonTaskifyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskify> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskify> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskify> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskify.class);
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
    public void saveAddressBook(ReadOnlyTaskify taskify) throws IOException {
        saveAddressBook(taskify, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyTaskify)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyTaskify taskify, Path filePath) throws IOException {
        requireNonNull(taskify);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskify(taskify), filePath);
    }

}
