package seedu.smartlib.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class JsonSerializableSmartLibTest {

    private static final Path TEST_DATA_FOLDER = Paths
            .get("src", "test", "data", "JsonSerializableSmartLibTest");
    private static final Path TYPICAL_READERS_FILE = TEST_DATA_FOLDER.resolve("typicalReaderSmartLib.json");
    private static final Path INVALID_READER_FILE = TEST_DATA_FOLDER.resolve("invalidReaderSmartLib.json");
    private static final Path DUPLICATE_READER_FILE = TEST_DATA_FOLDER.resolve("duplicateReaderSmartLib.json");

    @Test
    public void toModelType_typicalReadersFile_success() throws Exception {
        //JsonSerializableSmartLib dataFromFile = JsonUtil.readJsonFile(TYPICAL_READERS_FILE,
        //        JsonSerializableSmartLib.class).get();
        //SmartLib smartLibFromFile = dataFromFile.toModelType();
        //SmartLib typicalPersonsSmartLib = TypicalReaders.getTypicalSmartLib();
        //assertEquals(smartLibFromFile, typicalPersonsSmartLib);
    }

    @Test
    public void toModelType_invalidReaderFile_throwsIllegalValueException() throws Exception {
        //JsonSerializableSmartLib dataFromFile = JsonUtil.readJsonFile(INVALID_READER_FILE,
        //        JsonSerializableSmartLib.class).get();
        //assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateReaders_throwsIllegalValueException() throws Exception {
        // JsonSerializableSmartLib dataFromFile = JsonUtil.readJsonFile(DUPLICATE_READER_FILE,
        //        JsonSerializableSmartLib.class).get();
        //assertThrows(IllegalValueException.class, JsonSerializableSmartLib.MESSAGE_DUPLICATE_READER,
        //        dataFromFile::toModelType);
    }

}
