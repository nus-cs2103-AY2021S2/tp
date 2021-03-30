package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tutorcommands.AddCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.BudgetBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.budget.Budget;
import seedu.address.model.event.Event;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.PersonFilter;
import seedu.address.model.grade.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorByName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
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
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
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
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
        public void removeAppointmentIndex(int indexToRemove) {
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
        public boolean hasPersonFilter(PersonFilter personFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonFilter(PersonFilter personFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removePersonFilter(PersonFilter personFilter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<String> getPersonFilterStringList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointmentFilter(AppointmentFilter appointmentFilter) {
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
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
