package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.shortcut.ShortcutLibrary;
import seedu.address.testutil.TypicalShortcuts;

public class JsonSerializableShortcutLibraryTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableShortcutLibraryTest");
    private static final Path TYPICAL_SHORTCUT_FILE = TEST_DATA_FOLDER.resolve("typicalShortcutLibrary.json");
    private static final Path INVALID_SHORTCUT_FILE = TEST_DATA_FOLDER.resolve("invalidShortcutLibrary.json");
    private static final Path DUPLICATE_SHORTCUT_FILE = TEST_DATA_FOLDER.resolve("duplicateShortcutLibrary.json");

    @Test
    public void toModelType_typicalShortcutsFile_success() throws Exception {
        JsonSerializableShortcutLibrary dataFromFile = JsonUtil.readJsonFile(TYPICAL_SHORTCUT_FILE,
                JsonSerializableShortcutLibrary.class).get();
        ShortcutLibrary shortcutLibraryFromFile = dataFromFile.toModelType();
        ShortcutLibrary typicalShortcutLibrary = TypicalShortcuts.getTypicalShortcutLibrary();
        assertEquals(shortcutLibraryFromFile, typicalShortcutLibrary);
    }

    @Test
    public void toModelType_invalidShortcutFile_throwsIllegalValueException() throws Exception {
        JsonSerializableShortcutLibrary dataFromFile = JsonUtil.readJsonFile(INVALID_SHORTCUT_FILE,
                JsonSerializableShortcutLibrary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateShortcuts_throwsIllegalValueException() throws Exception {
        JsonSerializableShortcutLibrary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SHORTCUT_FILE,
                JsonSerializableShortcutLibrary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableShortcutLibrary.MESSAGE_DUPLICATE_SHORTCUT,
                dataFromFile::toModelType);
    }
}
