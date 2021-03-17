package fooddiary.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.ReadOnlyUserPrefs;
import fooddiary.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyFoodDiary> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyFoodDiary addressBook) throws IOException;

}
