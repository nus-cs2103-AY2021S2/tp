package seedu.dictionote.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.commons.util.FileUtil;
import seedu.dictionote.commons.util.JsonUtil;
import seedu.dictionote.model.ReadOnlyContactsList;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonContactsListStorage implements ContactsListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonContactsListStorage.class);

    private Path filePath;

    public JsonContactsListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getContactsListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyContactsList> readContactsList() throws DataConversionException {
        return readContactsList(filePath);
    }

    /**
     * Similar to {@link #readContactsList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyContactsList> readContactsList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableContactsList> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableContactsList.class);
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
    public void saveContactsList(ReadOnlyContactsList addressBook) throws IOException {
        saveContactsList(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveContactsList(ReadOnlyContactsList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveContactsList(ReadOnlyContactsList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableContactsList(addressBook), filePath);
    }

}
