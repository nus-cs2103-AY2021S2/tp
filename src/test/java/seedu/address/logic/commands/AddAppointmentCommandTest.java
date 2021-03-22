package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyStudentBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.StudentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        Person validPerson = new PersonBuilder().build();
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded(validPerson);
        Appointment validAppointment = new AppointmentBuilder()
                .withMatric(validPerson.getMatriculationNumber().toString()).build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Appointment validAppointment = new AppointmentBuilder()
                .withMatric(validPerson.getMatriculationNumber().toString()).build();

        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithPersonAndAppointment(validPerson, validAppointment);

        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment appt1 = new AppointmentBuilder().withMatric("A1234567X").build();
        Appointment appt2 = new AppointmentBuilder().withMatric("A7654321Y").build();
        AddAppointmentCommand addAppt1Command = new AddAppointmentCommand(appt1);
        AddAppointmentCommand addAppt2Command = new AddAppointmentCommand(appt2);

        // same object -> returns true
        assertTrue(addAppt1Command.equals(addAppt1Command));

        // same values -> returns true
        AddAppointmentCommand addAppt1CommandCopy = new AddAppointmentCommand(appt1);
        assertTrue(addAppt1Command.equals(addAppt1CommandCopy));

        // different types -> returns false
        assertFalse(addAppt1Command.equals(1));

        // null -> returns false
        assertFalse(addAppt1Command.equals(null));

        // different person -> returns false
        assertFalse(addAppt1Command.equals(addAppt2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
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
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyStudentBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyStudentBook getAddressBook() {
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
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithPersonAndAppointment extends ModelStub {
        private final Person person;
        private final Appointment appointment;

        ModelStubWithPersonAndAppointment(Person person, Appointment appointment) {
            requireAllNonNull(person, appointment);
            this.person = person;
            this.appointment = appointment;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Person> persons = new ArrayList<>();
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        public ModelStubAcceptingAppointmentAdded(Person person) {
            persons.add(person);
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public boolean hasOverlappingAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::doesTimeOverlap);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyStudentBook getAddressBook() {
            return new StudentBook();
        }
    }

}
