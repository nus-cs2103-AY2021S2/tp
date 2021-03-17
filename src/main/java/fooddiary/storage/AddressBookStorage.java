package fooddiary.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;

/**
 * Represents a storage for {@link FoodDiary}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFoodDiary}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodDiary> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyFoodDiary> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFoodDiary} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyFoodDiary addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyFoodDiary)
     */
    void saveAddressBook(ReadOnlyFoodDiary addressBook, Path filePath) throws IOException;

}
