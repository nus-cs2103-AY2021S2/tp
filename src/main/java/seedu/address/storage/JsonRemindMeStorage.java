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
import seedu.address.model.ReadOnlyRemindMe;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonRemindMeStorage implements RemindMeStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRemindMeStorage.class);

    private Path filePath;

    public JsonRemindMeStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRemindMeFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRemindMe> readRemindMe() throws DataConversionException, IOException {
        return readRemindMe(filePath);
    }

    /**
     * Similar to {@link #readRemindMe()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRemindMe> readRemindMe(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRemindMe> jsonRemindMeApp = JsonUtil.readJsonFile(
                filePath, JsonSerializableRemindMe.class);
        if (!jsonRemindMeApp.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRemindMeApp.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRemindMe(ReadOnlyRemindMe remindMe) throws IOException {
        saveRemindMe(remindMe, filePath);
    }

    /**
     * Similar to {@link #saveRemindMe(ReadOnlyRemindMe)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRemindMe(ReadOnlyRemindMe remindMeApp, Path filePath) throws IOException {
        requireNonNull(remindMeApp);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRemindMe(remindMeApp), filePath);
    }

}
