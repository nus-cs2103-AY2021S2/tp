package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Doctor;
import seedu.address.testutil.TypicalAppObjects;

public class JsonSerializableDoctorRecordsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableDoctorRecordsTest");
    private static final Path TYPICAL_DOCTORS_FILE = TEST_DATA_FOLDER.resolve("typicalDoctorRecords.json");
    private static final Path INVALID_DOCTORS_FILE = TEST_DATA_FOLDER.resolve("invalidDoctorRecords.json");
    private static final Path DUPLICATE_DOCTORS_FILE = TEST_DATA_FOLDER.resolve("duplicateDoctorRecords.json");

    @Test
    public void toModelType_typicalDoctorsFile_success() throws Exception {
        JsonSerializableDoctorRecords dataFromFile = JsonUtil.readJsonFile(TYPICAL_DOCTORS_FILE,
                JsonSerializableDoctorRecords.class).get();
        AddressBook<Doctor> addressBookFromFile = dataFromFile.toModelType();
        AddressBook<Doctor> typicalDoctorRecords = TypicalAppObjects.getTypicalDoctorRecords();
        assertEquals(addressBookFromFile, typicalDoctorRecords);
    }

    @Test
    public void toModelType_invalidDoctorsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDoctorRecords dataFromFile = JsonUtil.readJsonFile(INVALID_DOCTORS_FILE,
                JsonSerializableDoctorRecords.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateDoctors_throwsIllegalValueException() throws Exception {
        JsonSerializableDoctorRecords dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DOCTORS_FILE,
                JsonSerializableDoctorRecords.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDoctorRecords.MESSAGE_DUPLICATE_DOCTOR,
                dataFromFile::toModelType);
    }

}
