package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AppointmentBook;
import seedu.address.model.GradeBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.TutorBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.ScheduleTracker;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTutorBookStorage tutorBookStorage = new JsonTutorBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonAppointmentBookStorage appointmentBookStorage =
                new JsonAppointmentBookStorage(getTempFilePath("app"));
        JsonGradeBookStorage gradeBookStorage = new JsonGradeBookStorage(getTempFilePath("gr"));

        JsonScheduleTrackerStorage scheduleTrackerStorage = new JsonScheduleTrackerStorage(getTempFilePath("st"));
        JsonReminderTrackerStorage reminderTrackerStorage = new JsonReminderTrackerStorage(getTempFilePath("rt"));

        storageManager = new StorageManager(tutorBookStorage, userPrefsStorage,
                appointmentBookStorage, gradeBookStorage, scheduleTrackerStorage, reminderTrackerStorage);
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
    public void tutorBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTutorBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTutorBookStorageTest} class.
         */
        TutorBook original = getTypicalTutorBook();
        storageManager.saveTutorBook(original);
        ReadOnlyTutorBook retrieved = storageManager.readTutorBook().get();
        assertEquals(original, new TutorBook(retrieved));
    }

    @Test
    public void getTutorBookFilePath() {
        assertNotNull(storageManager.getTutorBookFilePath());
    }

    @Test
    public void appointmentBookReadSave() throws Exception {
        AppointmentBook original = getTypicalAppointmentBook();
        storageManager.saveAppointmentBook(original);
        ReadOnlyAppointmentBook retrieved = storageManager.readAppointmentBook().get();
        assertEquals(original, new AppointmentBook(retrieved));
    }

    @Test
    public void scheduleTrackerReadSave() throws Exception {
        ScheduleTracker original = getTypicalScheduleTracker();
        storageManager.saveScheduleTracker(original);
        ReadOnlyScheduleTracker retrieved = storageManager.readScheduleTracker().get();
        assertEquals(original, new ScheduleTracker(retrieved));
    }

    @Test
    public void gradeBookReadSave() throws Exception {
        GradeBook original = getTypicalGradeBook();
        storageManager.saveGradeBook(original);
        ReadOnlyGradeBook retrieved = storageManager.readGradeBook().get();
        assertEquals(original, new GradeBook(retrieved));
    }

    @Test
    public void getAppointmentBookFilePath() {
        assertNotNull(storageManager.getAppointmentBookFilePath());
    }

    @Test
    public void getGradeBookFilePath() {
        assertNotNull(storageManager.getGradeBookFilePath());
    }

    @Test
    public void getScheduleTrackerFilePath() {
        assertNotNull(storageManager.getScheduleTrackerFilePath());
    }

}
