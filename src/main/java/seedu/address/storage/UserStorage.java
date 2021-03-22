package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.user.User;

/**
 * Represents a storage for the {@link User}.
 */
public interface UserStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUserFilePath();

    /**
     * Returns User data as a {@link User}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<User> readUser() throws DataConversionException, IOException;

    /**
     * @see #getUserFilePath()
     */
    Optional<User> readUser(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link DietUser} to the storage.
     * @param dietPlanList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUser(User user) throws IOException;

    /**
     * @see #saveUser(User)
     */
    void saveUser(User user, Path filePath) throws IOException;

}
