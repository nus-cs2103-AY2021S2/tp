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
import seedu.address.model.user.User;

/**
 * A class to access User data stored as a json file on the hard disk.
 */
public class JsonUserStorage implements UserStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDietPlanListStorage.class);

    private Path filePath;

    public JsonUserStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserFilePath() {
        return filePath;
    }

    @Override
    public Optional<User> readUser() throws DataConversionException, IOException {
        return readUser(filePath);
    }


    /**
     * Similar to {@link #readUser()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<User> readUser(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUser> jsonUser = JsonUtil.readJsonFile(
                filePath, JsonSerializableUser.class);
        if (!jsonUser.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUser.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }


    @Override
    public void saveUser(User user) throws IOException {
        saveUser(user, filePath);
    }

    /**
     * Similar to {@link #saveUser(User)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUser(User user, Path filePath) throws IOException {
        requireNonNull(user);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUser(user), filePath);
    }

}
