package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BudgetBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TutorBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.budget.Budget;
import seedu.address.model.event.Event;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.TutorFilter;
import seedu.address.model.grade.Grade;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Tutor;
import seedu.address.testutil.TutorBuilder;


public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Tutor validTutor = new TutorBuilder().build();

        CommandResult commandResult = new AddCommand(validTutor).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTutor), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTutor), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Tutor validTutor = new TutorBuilder().build();
        AddCommand addCommand = new AddCommand(validTutor);
        ModelStub modelStub = new ModelStubWithPerson(validTutor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tutor alice = new TutorBuilder().withName("Alice").build();
        Tutor bob = new TutorBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertEquals(addAliceCommandCopy, addAliceCommand);

        // different types -> returns false
        assertNotEquals(addAliceCommand, 1);

        // null -> returns false
        assertNotEquals(addAliceCommand, null);

        // different person -> returns false
        assertNotEquals(addBobCommand, addAliceCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTutorBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorBookFilePath(Path tutorBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorByName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutor(Tutor tutor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTutorBook getTutorBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorBook(ReadOnlyTutorBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAppointmentBook getAppointmentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentBook(ReadOnlyAppointmentBook readOnlyAppointmentBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointmentContainingTutor(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void changeAllAppointmentsToName(Name oldName, Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesAppointmentClash(Name name, AppointmentDateTime timeFrom, AppointmentDateTime timeTo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesTutorTeachSubject(Name name, SubjectName subjectName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAppointmentBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGradeBook getGradeBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGradeBook(ReadOnlyGradeBook readOnlyGradeBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getGradeBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGradeBookFilePath(Path gradeBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutor(Tutor tutor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutor(Tutor target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutor(Tutor target, Tutor editedTutor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutor> getFilteredTutorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Grade> getFilteredGradeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorList(Predicate<Tutor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGradeList(Predicate<Grade> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Appointment removeAppointmentIndex(int indexToRemove) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointmentDateTime(AppointmentDateTime appointmentDateTime) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public BudgetBook getBudgetBook() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasBudget() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasBudget(Budget budget) {
            throw new AssertionError("This method should not be called");
        }

        public boolean hasGrade(Grade grade) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGrade(Grade grade) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBudget(Budget budget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Budget> getBudgetList() {
            throw new AssertionError("This method should not be called.");
        }

        public void deleteGrade(Grade grade) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editBudget(Budget budget) {
            throw new AssertionError("This method should not be called.");
        }


        public void setGrade(Grade target, Grade editedGrade) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeGradeIndex(int indexToRemove) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAnyTutorFilter(TutorFilter tutorFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAllTutorFilters(TutorFilter tutorFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorFilter(TutorFilter tutorFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeTutorFilter(TutorFilter tutorFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<String> getTutorFilterStringList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAnyAppointmentFilter(AppointmentFilter appointmentFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAllAppointmentFilters(AppointmentFilter appointmentFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointmentFilter(AppointmentFilter appointmentFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeAppointmentFilter(AppointmentFilter appointmentFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<String> getAppointmentFilterStringList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyScheduleTracker getScheduleTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setScheduleTracker(ReadOnlyScheduleTracker scheduleTracker) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSchedule(Schedule schedule) {
            throw new AssertionError("This method should not be called.");
        }

        public void setSchedule(Schedule target, Schedule editedSchedule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingDateTime(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClashingDateTime(Event editedEvent, Event preEditEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTimeTableDate(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public LocalDate getTimeTableDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetPredicates() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyReminderTracker getReminderTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReminderTracker(ReadOnlyReminderTracker reminderTracker) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Reminder> getFilteredReminderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredReminderList(Predicate<Reminder> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReminder(Reminder target, Reminder editedReminder) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Tutor tutor;

        ModelStubWithPerson(Tutor tutor) {
            requireNonNull(tutor);
            this.tutor = tutor;
        }

        @Override
        public boolean hasTutor(Tutor tutor) {
            requireNonNull(tutor);
            return this.tutor.isSameTutor(tutor);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Tutor> personsAdded = new ArrayList<>();

        @Override
        public boolean hasTutor(Tutor tutor) {
            requireNonNull(tutor);
            return personsAdded.stream().anyMatch(tutor::isSameTutor);
        }

        @Override
        public void addTutor(Tutor tutor) {
            requireNonNull(tutor);
            personsAdded.add(tutor);
        }

        @Override
        public ReadOnlyTutorBook getTutorBook() {
            return new TutorBook();
        }
    }

}
