package seedu.heymatez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.heymatez.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.heymatez.commons.exceptions.IllegalValueException;
import seedu.heymatez.commons.util.JsonUtil;
import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.testutil.TypicalPersons;

public class JsonSerializableHeyMatezTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableHeyMatezTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsHeyMatez.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonHeyMatez.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonHeyMatez.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableHeyMatez dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableHeyMatez.class).get();
        HeyMatez addressBookFromFile = dataFromFile.toModelType();
        HeyMatez typicalPersonsAddressBook = TypicalPersons.getTypicalHeyMatez();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableHeyMatez dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableHeyMatez.class).get();

        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableHeyMatez dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableHeyMatez.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableHeyMatez.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
