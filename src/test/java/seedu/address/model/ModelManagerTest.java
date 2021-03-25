package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.session.Session;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.SessionBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void hasName_nameExists_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasName(ALICE.getName()));
    }

    @Test
    public void hasName_nameDoesNotExist_returnsFalse() {
        modelManager.addStudent(BOB);
        assertTrue(modelManager.hasName(BOB.getName()));
    }

    @Test
    public void hasSession_sessionExists_returnsTrue() {
        Session session = new SessionBuilder().build();
        modelManager.addStudent(ALICE);
        modelManager.addSession(ALICE.getName(), session);
        assertTrue(modelManager.hasSession(session));
    }

    @Test
    public void hasSession_sessionDoesNotExist_returnsFalse() {
        Session session = new SessionBuilder().build();
        Session newSession = new SessionBuilder().withSessionDate("2022-01-01", "00:00").build();
        modelManager.addStudent(ALICE);
        modelManager.addSession(ALICE.getName(), session);
        assertFalse(modelManager.hasSession(newSession));
    }

    @Test
    public void hasSession_sessionWithSameDateAndTimeWithDifferentFields_returnsTrue() {
        Session session = new SessionBuilder().build();
        Session newSession = new SessionBuilder().withSubject("Computer Science").withFee("80").build();
        modelManager.addStudent(ALICE);
        modelManager.addSession(ALICE.getName(), session);
        assertTrue(modelManager.hasSession(newSession));
    }

    @Test
    public void hasOverlappingSession_sessionOverlaps_returnsTrue() {
        Session session = new SessionBuilder().build();
        Session sessionOneHourLater = new SessionBuilder().withSessionDate("2021-01-01", "01:00").build();
        modelManager.addStudent(ALICE);
        modelManager.addSession(ALICE.getName(), session);
        assertTrue(modelManager.hasOverlappingSession(sessionOneHourLater));
    }

    @Test
    public void hasOverlappingSession_sameSession_returnsTrue() {
        Session session = new SessionBuilder().build();
        modelManager.addStudent(ALICE);
        modelManager.addSession(ALICE.getName(), session);
        assertTrue(modelManager.hasOverlappingSession(session));
    }

    @Test
    public void hasOverlappingSession_sessionNotOverlapping_returnsFalse() {
        Session session = new SessionBuilder().build();
        Session newSession = new SessionBuilder().withSessionDate("2022-01-01", "00:00").build();
        modelManager.addStudent(ALICE);
        modelManager.addSession(ALICE.getName(), session);
        assertFalse(modelManager.hasOverlappingSession(newSession));
    }

    @Test
    public void hasOverlappingSession_sessionStartsAtSessionEnd_returnsFalse() {
        Session session = new SessionBuilder().build();
        Session newSession = new SessionBuilder().withSessionDate("2022-01-01", "01:30").build();
        modelManager.addStudent(ALICE);
        modelManager.addSession(ALICE.getName(), session);
        assertFalse(modelManager.hasOverlappingSession(newSession));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withStudent(ALICE).withStudent(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
