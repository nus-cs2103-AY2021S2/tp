package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalReminders.MATHS_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalReminders.SCIENCE_TUITION_PAYMENT_REMINDER;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.SCIENCE_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalTutors.ALICE;
import static seedu.address.testutil.TypicalTutors.BENSON;

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
import seedu.address.model.reminder.ReminderTracker;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.NameContainsKeywordsPredicate;
import seedu.address.testutil.ReminderTrackerBuilder;
import seedu.address.testutil.ScheduleTrackerBuilder;
import seedu.address.testutil.TutorBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TutorBook(), new TutorBook(modelManager.getTutorBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTutorBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTutorBookFilePath(Paths.get("new/address/book/file/path"));
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
        assertThrows(NullPointerException.class, () -> modelManager.setTutorBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTutorBookFilePath(path);
        assertEquals(path, modelManager.getTutorBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTutor(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTutor(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addTutor(ALICE);
        assertTrue(modelManager.hasTutor(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTutorList().remove(0));
    }

    @Test
    public void equals() {
        TutorBook addressBook = new TutorBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        TutorBook differentAddressBook = new TutorBook();

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
        GradeEnum grade = GradeEnum.valueOf("A1");
        Grade diffGrade = new Grade(subjectName, gradedItem, grade);
        GradeBook differentGradeBook = new GradeBook();
        differentGradeBook.addGrade(diffGrade);

        ScheduleTracker scheduleTracker = new ScheduleTrackerBuilder().withSchedule(MATHS_HOMEWORK_SCHEDULE)
                .withSchedule(SCIENCE_HOMEWORK_SCHEDULE).build();
        ScheduleTracker differentScheduleTracker = new ScheduleTracker();

        ReminderTracker reminderTracker = new ReminderTrackerBuilder().withReminder(MATHS_TUITION_PAYMENT_REMINDER)
                .withReminder(SCIENCE_TUITION_PAYMENT_REMINDER).build();
        ReminderTracker differentReminderTracker = new ReminderTracker();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs, appointmentBook,
                budgetBook, gradeBook, scheduleTracker, reminderTracker);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs,
                appointmentBook, budgetBook, gradeBook, scheduleTracker, reminderTracker);

        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, appointmentBook, budgetBook, gradeBook, scheduleTracker, reminderTracker)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredTutorList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                appointmentBook, budgetBook, gradeBook, scheduleTracker, reminderTracker)));


        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTutorBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook,
                differentUserPrefs, appointmentBook, budgetBook, gradeBook, scheduleTracker, reminderTracker)));

        // different appointmentBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook,
                userPrefs, differentAppointmentBook, budgetBook, gradeBook, scheduleTracker, reminderTracker)));

        // different budget book -> returns false
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs,
                appointmentBook, diffBudgetBook, gradeBook, scheduleTracker, reminderTracker)));

        //different gradeBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, appointmentBook, budgetBook, differentGradeBook, scheduleTracker, reminderTracker)));

        //different schedule tracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, appointmentBook, budgetBook, gradeBook, differentScheduleTracker, reminderTracker)));

        //different reminder tracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook,
                userPrefs, appointmentBook, budgetBook, gradeBook, scheduleTracker, differentReminderTracker)));
    }
}
