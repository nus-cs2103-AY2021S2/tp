package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalAppointments.MEET_BOB;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.testutil.AppointmentBookBuilder;
import seedu.address.testutil.PropertyBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AppointmentBook(), new AppointmentBook(modelManager.getAppointmentBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAppointmentBookFilePath(Paths.get("appointment/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAppointmentBookFilePath(Paths.get("appointment/address/book/file/path"));
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
    public void setAppointmentBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAppointmentBookFilePath(null));
    }

    @Test
    public void setAppointmentBookFilePath_validPath_setsAppointmentBookFilePath() {
        Path path = Paths.get("appointment/book/file/path");
        modelManager.setAppointmentBookFilePath(path);
        assertEquals(path, modelManager.getAppointmentBookFilePath());
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAppointment(null));
    }

    @Test
    public void hasAppointment_appointmentNotInAppointmentBook_returnsFalse() {
        assertFalse(modelManager.hasAppointment(MEET_ALEX));
    }

    @Test
    public void hasAppointment_appointmentInAppointmentBook_returnsTrue() {
        modelManager.addAppointment(MEET_ALEX);
        assertTrue(modelManager.hasAppointment(MEET_ALEX));
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAppointmentList().remove(0));
    }

    @Test
    public void equals() {
        AppointmentBook appointmentBook = new AppointmentBookBuilder().withAppointment(MEET_ALEX)
                .withAppointment(MEET_BOB).build();
        AppointmentBook differentAppointmentBook = new AppointmentBook();
        PropertyBook propertyBook = new PropertyBookBuilder().withProperty(WOODLANDS_CRESCENT)
                .withProperty(MAYFAIR).build();
        PropertyBook differentPropertyBook = new PropertyBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(appointmentBook, propertyBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(appointmentBook, propertyBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different appointmentBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAppointmentBook, propertyBook, userPrefs)));

        //different propertyBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(appointmentBook, differentPropertyBook, userPrefs)));

        // different filteredAppointmentList -> returns false
        modelManager.updateFilteredAppointmentList(unused -> false);
        assertFalse(modelManager.equals(new ModelManager(appointmentBook, propertyBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        // different filteredPropertyList -> returns false
        modelManager.updateFilteredPropertyList(unused -> false);
        assertFalse(modelManager.equals(new ModelManager(appointmentBook, propertyBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAppointmentBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(appointmentBook, propertyBook, differentUserPrefs)));
    }
}
