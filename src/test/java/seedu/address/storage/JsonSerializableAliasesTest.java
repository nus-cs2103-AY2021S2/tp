package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.UniqueAliasMap;
import seedu.address.testutil.TypicalAliases;

public class JsonSerializableAliasesTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAliasesTest");
    private static final Path TYPICAL_ALIASES_FILE = TEST_DATA_FOLDER.resolve("typicalAliasesAliases.json");
    private static final Path INVALID_ALIAS_FILE = TEST_DATA_FOLDER.resolve("invalidAliasAliases.json");
    private static final Path DUPLICATE_ALIAS_FILE = TEST_DATA_FOLDER.resolve("duplicateAliasAliases.json");

    @Test
    public void toModelType_typicalAliasesFile_success() throws Exception {
        JsonSerializableAliases dataFromFile = JsonUtil.readJsonFile(TYPICAL_ALIASES_FILE,
                JsonSerializableAliases.class).get();
        UniqueAliasMap aliasesFromFile = dataFromFile.toModelType();
        UniqueAliasMap typicalAliasesAliases = TypicalAliases.getTypicalAliases();
        assertEquals(aliasesFromFile, typicalAliasesAliases);
    }

    @Test
    public void toModelType_invalidAliasFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAliases dataFromFile = JsonUtil.readJsonFile(INVALID_ALIAS_FILE,
                JsonSerializableAliases.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAliases_throwsIllegalValueException() throws Exception {
        JsonSerializableAliases dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ALIAS_FILE,
                JsonSerializableAliases.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAliases.MESSAGE_DUPLICATE_ALIAS,
                dataFromFile::toModelType);
    }

}
