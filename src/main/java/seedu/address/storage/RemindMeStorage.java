package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyModulePlanner;

public interface RemindMeStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRemindMeFilePath();

    /**
     * Returns RemindMe data as a {@link ReadOnlyModulePlanner}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModulePlanner> readRemindMe() throws DataConversionException, IOException;

    /**
     * @see #getRemindMeFilePath()
     */
    Optional<ReadOnlyModulePlanner> readRemindMe(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param remindMeApp cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRemindMe(ReadOnlyModulePlanner remindMeApp) throws IOException;

    /**
     * @see #saveRemindMe(ReadOnlyModulePlanner)
     */
    void saveRemindMe(ReadOnlyModulePlanner remindMeApp, Path filePath) throws IOException;

}
