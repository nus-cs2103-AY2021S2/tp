package seedu.address.storage.commandhistory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;
import seedu.address.testutil.TypicalCommandHistoryEntries;

/**
 * Contains unit tests for {@code PlainTextCommandHistoryStorage}.
 */
public class PlainTextCommandHistoryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "PlainTextCommandHistoryStorageTest");
    private static final String TEMP_FILE_PATH = "tempCmdHist.txt";
    private static final String EMPTY_HISTORY_PATH = "emptyCmdHist.txt";
    private static final String BLANK_LINES_HISTORY_PATH = "blankLinesCmdHist.txt";
    private static final String NON_EXISTENT_HISTORY_PATH = "nonExistentCmdHist.txt";

    @TempDir
    public Path testFolder;

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> getStorageWithPath(null));
    }

    @Test
    public void constructor_validPath_doesNotThrow() {
        assertDoesNotThrow(() -> getStorageWithPath(TEMP_FILE_PATH));
    }

    @Test
    public void getCommandHistoryFilePath_validPath_succeeds() {
        Path validPath = addToTestDataPathIfNotNull(TEMP_FILE_PATH);
        assertEquals(validPath, new PlainTextCommandHistoryStorage(validPath).getCommandHistoryFilePath());
    }

    @Test
    public void readCommandHistory_missingFile_returnsEmpty() {
        assertDoesNotThrow(() -> {
            assertTrue(getStorageWithPath(NON_EXISTENT_HISTORY_PATH).readCommandHistory().isEmpty());
        });
    }

    @Test
    public void read_blankLinesHistory_returnsEmptyHistory() throws IOException {
        ReadOnlyCommandHistory read = getStorageWithPath(BLANK_LINES_HISTORY_PATH).readCommandHistory().get();
        assertEquals(new CommandHistory(), read);
    }

    @Test
    public void read_emptyHistory_success() throws IOException {
        ReadOnlyCommandHistory read = getStorageWithPath(EMPTY_HISTORY_PATH).readCommandHistory().get();
        assertEquals(new CommandHistory(), read);
    }

    @Test
    public void saveAndRead_allInOrder_success() throws IOException {
        Path filePath = testFolder.resolve(TEMP_FILE_PATH);
        PlainTextCommandHistoryStorage storage = new PlainTextCommandHistoryStorage(filePath);
        CommandHistory typical = TypicalCommandHistoryEntries.getTypicalCommandHistory();

        // Write to empty file and check that what was written can be read
        storage.saveCommandHistory(typical);
        ReadOnlyCommandHistory readBack = storage.readCommandHistory().get();
        assertEquals(typical, readBack);

        // Modify existing data, save, read and check
        typical.appendEntry(TypicalCommandHistoryEntries.getRandomEntry());
        storage.saveCommandHistory(typical);
        readBack = storage.readCommandHistory().get();
        assertEquals(typical, readBack);
    }

    private Path addToTestDataPathIfNotNull(String userPrefsFileInTestDataFolder) {
        return userPrefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userPrefsFileInTestDataFolder)
                : null;
    }

    private PlainTextCommandHistoryStorage getStorageWithPath(String path) {
        Path pathMayBeNull = addToTestDataPathIfNotNull(path);
        return new PlainTextCommandHistoryStorage(pathMayBeNull);
    }
}
