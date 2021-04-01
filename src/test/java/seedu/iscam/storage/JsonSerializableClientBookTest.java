package seedu.iscam.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.iscam.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.commons.util.JsonUtil;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.testutil.TypicalClients;

public class JsonSerializableClientBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableClientBookTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsClientBook.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientClientBook.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientClientBook.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableClientBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableClientBook.class).get();
        ClientBook clientBookFromFile = dataFromFile.toModelType();
        ClientBook typicalClientsClientBook = TypicalClients.getTypicalLocationBook();
        assertEquals(clientBookFromFile, typicalClientsClientBook);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableClientBook dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableClientBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableClientBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableClientBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableClientBook.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

}
