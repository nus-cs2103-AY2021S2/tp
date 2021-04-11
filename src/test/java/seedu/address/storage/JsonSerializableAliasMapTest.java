package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.UniqueAliasMap;
import seedu.address.testutil.TypicalCommandAliases;

public class JsonSerializableAliasMapTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAliasMapTest");
    private static final Path TYPICAL_ALIAS_MAP_FILE = TEST_DATA_FOLDER.resolve("typicalCommandAliasesAliasMap.json");
    private static final Path INVALID_ALIAS_MAP_FILE = TEST_DATA_FOLDER.resolve("invalidCommandAliasesAliasMap.json");
    private static final Path DUPLICATE_ALIAS_MAP_FILE =
            TEST_DATA_FOLDER.resolve("duplicateCommandAliasesAliasMap.json");

    @Test
    public void toModelType_typicalAliasMapFile_success() throws Exception {
        JsonSerializableAliasMap dataFromFile = JsonUtil.readJsonFile(TYPICAL_ALIAS_MAP_FILE,
                JsonSerializableAliasMap.class).get();
        UniqueAliasMap aliasMapFromFile = dataFromFile.toModelType();
        UniqueAliasMap typicalCommandAliasesAliasMap = TypicalCommandAliases.getTypicalAliasMap();
        assertEquals(aliasMapFromFile, typicalCommandAliasesAliasMap);
    }

    @Test
    public void toModelType_invalidAliasFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAliasMap dataFromFile = JsonUtil.readJsonFile(INVALID_ALIAS_MAP_FILE,
                JsonSerializableAliasMap.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAliasMap_throwsIllegalValueException() throws Exception {
        JsonSerializableAliasMap dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ALIAS_MAP_FILE,
                JsonSerializableAliasMap.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAliasMap.MESSAGE_DUPLICATE_ALIAS,
                dataFromFile::toModelType);
    }

}
