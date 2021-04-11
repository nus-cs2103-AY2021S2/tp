package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Doctor;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPatientRecordsStorage patientRecordsStorage = new JsonPatientRecordsStorage(
                getTempFilePath("PatientRecords"));
        JsonDoctorRecordsStorage doctorRecordsStorage = new JsonDoctorRecordsStorage(
                getTempFilePath("DoctorRecords"));
        JsonAppointmentScheduleStorage appointmentScheduleStorage = new JsonAppointmentScheduleStorage(
                getTempFilePath("AppointmentSchedule"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(
                getTempFilePath("UserPrefs"));

        storageManager = new StorageManager(patientRecordsStorage, doctorRecordsStorage,
                appointmentScheduleStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void patientRecordsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook<Patient> original = getTypicalPatientRecords();
        storageManager.savePatientRecords(original);
        ReadOnlyAddressBook<Patient> retrieved = storageManager.readPatientRecords().get();
        assertEquals(original, new AddressBook<>(retrieved));
    }

    @Test
    public void getPatientRecordsFilePath() {
        assertNotNull(storageManager.getPatientRecordsFilePath());
    }

    @Test
    public void doctorRecordsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook<Doctor> original = getTypicalDoctorRecords();
        storageManager.saveDoctorRecords(original);
        ReadOnlyAddressBook<Doctor> retrieved = storageManager.readDoctorRecords().get();
        assertEquals(original, new AddressBook<>(retrieved));
    }

    @Test
    public void getDoctorRecordsFilePath() {
        assertNotNull(storageManager.getDoctorRecordsFilePath());
    }

}
