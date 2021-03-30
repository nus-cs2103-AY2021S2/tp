package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.SCIENCE_HOMEWORK_SCHEDULE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.budget.Budget;
import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeEnum;
import seedu.address.model.grade.GradedItem;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.model.subject.SubjectName;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.ScheduleTrackerBuilder;

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
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();

        UserPrefs userPrefs = new UserPrefs();

        AppointmentBook appointmentBook = new AppointmentBook();
        AppointmentDateTime appointmentFromTime =
                new AppointmentDateTime("2020-10-10 10:10AM");
        AppointmentDateTime appointmentToTime =
                new AppointmentDateTime("2020-10-10 11:10AM");
        Appointment diffAppointment = new Appointment(new Name("John Lim"),
                new SubjectName("Math"), appointmentFromTime, appointmentToTime,
                new Address("Clementi"));
        AppointmentBook differentAppointmentBook = new AppointmentBook();
        differentAppointmentBook.addAppointment(diffAppointment);

        BudgetBook budgetBook = new BudgetBook(new Budget("500"));
        BudgetBook diffBudgetBook = new BudgetBook(new Budget("5000"));


        GradeBook gradeBook = new GradeBook();
        SubjectName subjectName = new SubjectName("Mathematics");
        GradedItem gradedItem = new GradedItem("final exam");
        GradeEnum grade = GradeEnum.valueOf("A");
        Grade diffGrade = new Grade(subjectName, gradedItem, grade);
        GradeBook differentGradeBook = new GradeBook();
        differentGradeBook.addGrade(diffGrade);

        ScheduleTracker scheduleTracker = new ScheduleTrackerBuilder().withSchedule(MATHS_HOMEWORK_SCHEDULE)
                .withSchedule(SCIENCE_HOMEWORK_SCHEDULE).build();
        ScheduleTracker differentScheduleTracker = new ScheduleTracker();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs, appointmentBook,
         budgetBook, gradeBook, scheduleTracker);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs,
                appointmentBook, budgetBook, gradeBook, scheduleTracker);

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, appointmentBook, budgetBook, gradeBook, scheduleTracker)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                appointmentBook, budgetBook, gradeBook, scheduleTracker)));


        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook,
                differentUserPrefs, appointmentBook, budgetBook, gradeBook, scheduleTracker)));

        // different appointmentBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook,
                userPrefs, differentAppointmentBook, budgetBook, gradeBook, scheduleTracker)));

        // different budget book -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                appointmentBook, diffBudgetBook, gradeBook, scheduleTracker)));

        //different gradeBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, appointmentBook, budgetBook, differentGradeBook, scheduleTracker)));

        //different schedule tracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, appointmentBook, budgetBook, gradeBook, differentScheduleTracker)));
    }
}
