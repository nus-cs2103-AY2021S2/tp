package seedu.us.among.storage;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.us.among.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.model.EndpointList;
import seedu.us.among.testutil.TypicalEndpoints;
// import seedu.us.among.model.EndpointList;
// import seedu.us.among.testutil.TypicalEndpoints;

public class JsonSerializableEndpointListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEndpointListTest");
    private static final Path TYPICAL_ENDPOINT_FILE = TEST_DATA_FOLDER.resolve("typicalEndpointList.json");
    private static final Path INVALID_ENDPOINT_FILE = TEST_DATA_FOLDER.resolve("invalidEndpointList.json");
    private static final Path DUPLICATE_ENDPOINT_FILE = TEST_DATA_FOLDER.resolve("duplicateEndpointList.json");

    @Test
    public void toModelType_typicalEndpointsFile_success() throws Exception {
        JsonSerializableEndpointList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ENDPOINT_FILE,
             JsonSerializableEndpointList.class).get();
        EndpointList endpointListFromFile = dataFromFile.toModelType();
        EndpointList typicalEndpointList = TypicalEndpoints.getTypicalEndpointList();
        assertEquals(endpointListFromFile, typicalEndpointList);
    } //to-do

    @Test
    public void toModelType_invalidEndpointFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEndpointList dataFromFile = JsonUtil.readJsonFile(INVALID_ENDPOINT_FILE,
                JsonSerializableEndpointList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEndpoints_throwsIllegalValueException() throws Exception {
        JsonSerializableEndpointList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ENDPOINT_FILE,
             JsonSerializableEndpointList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEndpointList.MESSAGE_DUPLICATE_ENDPOINT,
             dataFromFile::toModelType);
    }

}
