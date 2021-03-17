package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Wardrobe;
import seedu.address.testutil.TypicalGarments;

public class JsonSerializableWardrobeTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableWardrobeTest");
    private static final Path TYPICAL_GARMENTS_FILE = TEST_DATA_FOLDER.resolve("typicalGarmentsWardrobe.json");
    private static final Path INVALID_GARMENT_FILE = TEST_DATA_FOLDER.resolve("invalidGarmentWardrobe.json");
    private static final Path DUPLICATE_GARMENT_FILE = TEST_DATA_FOLDER.resolve("duplicateGarmentWardrobe.json");

    @Test
    public void toModelType_typicalGarmentsFile_success() throws Exception {
        JsonSerializableWardrobe dataFromFile = JsonUtil.readJsonFile(TYPICAL_GARMENTS_FILE,
                JsonSerializableWardrobe.class).get();
        Wardrobe wardrobeFromFile = dataFromFile.toModelType();
        Wardrobe typicalGarmentsWardrobe = TypicalGarments.getTypicalWardrobe();
        assertEquals(wardrobeFromFile, typicalGarmentsWardrobe);
    }

    @Test
    public void toModelType_invalidGarmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableWardrobe dataFromFile = JsonUtil.readJsonFile(INVALID_GARMENT_FILE,
                JsonSerializableWardrobe.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGarments_throwsIllegalValueException() throws Exception {
        JsonSerializableWardrobe dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GARMENT_FILE,
                JsonSerializableWardrobe.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableWardrobe.MESSAGE_DUPLICATE_GARMENT,
                dataFromFile::toModelType);
    }

}
