package seedu.dictionote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.ReadOnlyContactsList;

/**
 * Represents a storage for {@link ContactsList}.
 */
public interface ContactsListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getContactsListFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyContactsList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyContactsList> readContactsList() throws DataConversionException, IOException;

    /**
     * @see #getContactsListFilePath()
     */
    Optional<ReadOnlyContactsList> readContactsList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyContactsList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveContactsList(ReadOnlyContactsList addressBook) throws IOException;

    /**
     * @see #saveContactsList(ReadOnlyContactsList)
     */
    void saveContactsList(ReadOnlyContactsList addressBook, Path filePath) throws IOException;

}
