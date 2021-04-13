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
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * A class to access ShortcutLibrary data stored as a json file on the hard disk.
 */
public class JsonShortcutLibraryStorage implements ShortcutLibraryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonShortcutLibraryStorage.class);

    private Path filePath;

    public JsonShortcutLibraryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getShortcutLibraryFilePath() {
        return filePath;
    }

    public Optional<ShortcutLibrary> readShortcutLibrary() throws DataConversionException {
        return readShortcutLibrary(filePath);
    }

    /**
     * Similar to {@link #readShortcutLibrary()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ShortcutLibrary> readShortcutLibrary(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableShortcutLibrary> jsonShortcutLibrary = JsonUtil.readJsonFile(
                filePath, JsonSerializableShortcutLibrary.class);
        if (jsonShortcutLibrary.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonShortcutLibrary.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveShortcutLibrary(ShortcutLibrary shortcutLibrary) throws IOException {
        saveShortcutLibrary(shortcutLibrary, filePath);
    }

    /**
     * Similar to {@link #saveShortcutLibrary(ShortcutLibrary)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveShortcutLibrary(ShortcutLibrary shortcutLibrary, Path filePath) throws IOException {
        requireNonNull(shortcutLibrary);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableShortcutLibrary(shortcutLibrary), filePath);
    }

}
