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
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAppointmentBookStorage implements ParentPalStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAppointmentBookStorage.class);

    private Path filePath;

    public JsonAppointmentBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAppointmentBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException {
        return readAppointmentBook(filePath);
    }

    /**
     * Similar to {@link #readAppointmentBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAppointmentBook> jsonAppointmentBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAppointmentBook.class);

        if (!jsonAppointmentBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAppointmentBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException {
        saveAppointmentBook(appointmentBook, filePath);
    }

    /**
     * Similar to {@link #saveAppointmentBook(ReadOnlyAppointmentBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, Path filePath) throws IOException {
        requireNonNull(appointmentBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAppointmentBook(appointmentBook), filePath);
    }


    // ================ AddressBook methods ==============================


    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getAddressBookFilePath() {
        return null;
    }

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return Optional.empty();
    }

    /**
     * @param filePath
     * @see #getAddressBookFilePath()
     */
    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        return Optional.empty();
    }

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     *
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {

    }

    /**
     * @param addressBook
     * @param filePath
     * @see #saveAddressBook(ReadOnlyAddressBook)
     */
    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {

    }

}
