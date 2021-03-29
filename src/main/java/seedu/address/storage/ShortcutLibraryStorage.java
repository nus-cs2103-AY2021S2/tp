package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.shortcut.ShortcutLibrary;

/**
 * Represents a storage for {@link seedu.address.model.shortcut.ShortcutLibrary}.
 */
public interface ShortcutLibraryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getShortcutLibraryFilePath();

    /**
     * Returns ShortcutLibrary data as a {@link ShortcutLibrary}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ShortcutLibrary> readShortcutLibrary() throws DataConversionException, IOException;

    /**
     * @see #getShortcutLibraryFilePath()
     */
    Optional<ShortcutLibrary> readShortcutLibrary(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ShortcutLibrary} to the storage.
     * @param shortcutLibrary cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveShortcutLibrary(ShortcutLibrary shortcutLibrary) throws IOException;

    /**
     * @see #saveShortcutLibrary(ShortcutLibrary)
     */
    void saveShortcutLibrary(ShortcutLibrary shortcutLibrary, Path filePath) throws IOException;

}
