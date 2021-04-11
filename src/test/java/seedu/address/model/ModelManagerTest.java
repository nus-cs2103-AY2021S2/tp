package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppObjects.ALICE;
import static seedu.address.testutil.TypicalAppObjects.BENSON;
import static seedu.address.testutil.TypicalAppObjects.DR_GREY;
import static seedu.address.testutil.TypicalAppObjects.DR_WHO;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Patient;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.AppointmentScheduleBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook<Patient>(), new AddressBook<Patient>(modelManager.getPatientRecords()));
        assertEquals(new AddressBook<Doctor>(), new AddressBook<Doctor>(modelManager.getDoctorRecords()));
        assertEquals(new AppointmentSchedule(), new AppointmentSchedule(modelManager.getAppointmentSchedule()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setPatientRecordsFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setPatientRecordsFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setPatientRecordsFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPatientRecordsFilePath(null));
    }

    @Test
    public void setPatientRecordsFilePath_validPath_setsPatientRecordsFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setPatientRecordsFilePath(path);
        assertEquals(path, modelManager.getPatientRecordsFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPatient(null));
    }

    @Test
    public void hasPerson_personNotInPatientRecords_returnsFalse() {
        assertFalse(modelManager.hasPatient(ALICE));
    }

    @Test
    public void hasPerson_personInPatientRecords_returnsTrue() {
        modelManager.addPatient(ALICE);
        assertTrue(modelManager.hasPatient(ALICE));
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPatientList().remove(0));
    }

    @Test
    public void getFilteredDoctorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredDoctorList().remove(0));
    }

    @Test
    public void equals() {
        AppointmentSchedule appointmentSchedule = new AppointmentScheduleBuilder().build();
        AddressBook<Patient> patientRecords = new AddressBookBuilder<Patient>()
                .withPerson(ALICE).withPerson(BENSON).build();
        AddressBook<Patient> differentPatientRecords = new AddressBook<>();
        AddressBook<Doctor> doctorRecords = new AddressBookBuilder<Doctor>()
                .withPerson(DR_GREY).withPerson(DR_WHO).build();
        AddressBook<Doctor> differentDoctorRecords = new AddressBook<>();
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setPatientRecordsFilePath(Paths.get("differentFilePath"));

        // same values -> returns true
        modelManager = new ModelManager(patientRecords, doctorRecords, appointmentSchedule, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(
            new AddressBook<>(patientRecords),
            new AddressBook<>(doctorRecords),
            new AppointmentSchedule(appointmentSchedule),
            new UserPrefs(userPrefs)
        );

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different patient records -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentPatientRecords, doctorRecords,
                appointmentSchedule, userPrefs)));

        // different doctor records -> returns false
        assertFalse(modelManager.equals(new ModelManager(patientRecords, differentDoctorRecords,
                appointmentSchedule, userPrefs)));

        // different userPrefs -> returns false
        assertFalse(modelManager.equals(new ModelManager(patientRecords, doctorRecords,
                appointmentSchedule, differentUserPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(modelManagerCopy));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }
}
