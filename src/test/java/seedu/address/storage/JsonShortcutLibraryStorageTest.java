package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalShortcuts.getTypicalShortcutLibrary;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.shortcut.ShortcutLibrary;

public class JsonShortcutLibraryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonShortcutLibraryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readShortcutLibrary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readShortcutLibrary(null));
    }

    private Optional<ShortcutLibrary> readShortcutLibrary(String filePath) throws Exception {
        return new JsonShortcutLibraryStorage(Paths.get(filePath)).readShortcutLibrary(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readShortcutLibrary("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readShortcutLibrary("notJsonFormatShortcutLibrary.json"));
    }

    @Test
    public void readShortcutLibrary_invalidShortcutLibrary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readShortcutLibrary("invalidShortcutLibrary.json"));
    }

    @Test
    public void readShortcutLibrary_invalidAndValidShortcutLibrary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readShortcutLibrary("invalidAndValidShortcutLibrary.json"));
    }

    @Test
    public void readAndSaveShortcutLibrary_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempShortcutLibrary.json");
        ShortcutLibrary original = getTypicalShortcutLibrary();
        JsonShortcutLibraryStorage jsonShortcutLibraryStorage = new JsonShortcutLibraryStorage(filePath);

        // Save in new file and read back
        jsonShortcutLibraryStorage.saveShortcutLibrary(original, filePath);
        ShortcutLibrary readBack = jsonShortcutLibraryStorage.readShortcutLibrary(filePath).get();
        assertEquals(original, new ShortcutLibrary(readBack.getShortcuts()));

        // Modify data, overwrite exiting file, and read back
        original.addShortcut("listaddress", "list -a");
        original.removeShortcut("sna");
        jsonShortcutLibraryStorage.saveShortcutLibrary(original, filePath);
        readBack = jsonShortcutLibraryStorage.readShortcutLibrary(filePath).get();
        assertEquals(original, new ShortcutLibrary(readBack.getShortcuts()));

        // Save and read without specifying file path
        original.addShortcut("findbedok", "find a/bedok");
        jsonShortcutLibraryStorage.saveShortcutLibrary(original); // file path not specified
        readBack = jsonShortcutLibraryStorage.readShortcutLibrary().get(); // file path not specified
        assertEquals(original, new ShortcutLibrary(readBack.getShortcuts()));

    }

    @Test
    public void saveShortcutLibrary_nullShortcutLibrary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveShortcutLibrary(null, "SomeFile.json"));
    }

    /**
     * Saves {@code ShortcutLibrary} at the specified {@code filePath}.
     */
    private void saveShortcutLibrary(ShortcutLibrary shortcutLibrary, String filePath) {
        try {
            new JsonShortcutLibraryStorage(Paths.get(filePath))
                    .saveShortcutLibrary(shortcutLibrary, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveShortcutLibrary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveShortcutLibrary(new ShortcutLibrary(), null));
    }
}
