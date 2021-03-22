package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Patient;
import seedu.address.testutil.TypicalAppObjects;

public class JsonSerializablePatientRecordsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializablePatientRecordsTest");
    private static final Path TYPICAL_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientRecords.json");
    private static final Path INVALID_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("invalidPatientRecords.json");
    private static final Path DUPLICATE_PATIENTS_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientRecords.json");

    @Test
    public void toModelType_typicalPatientsFile_success() throws Exception {
        JsonSerializablePatientRecords dataFromFile = JsonUtil.readJsonFile(TYPICAL_PATIENTS_FILE,
                JsonSerializablePatientRecords.class).get();
        AddressBook<Patient> addressBookFromFile = dataFromFile.toModelType();
        AddressBook<Patient> typicalPatientRecords = TypicalAppObjects.getTypicalPatientRecords();
        assertEquals(addressBookFromFile, typicalPatientRecords);
    }

    @Test
    public void toModelType_invalidPatientsFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientRecords dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENTS_FILE,
                JsonSerializablePatientRecords.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializablePatientRecords dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENTS_FILE,
                JsonSerializablePatientRecords.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePatientRecords.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
