package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.property.Property;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder().build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class,
                AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment meetAlex = new AppointmentBuilder().withName("Meet Alex").build();
        Appointment meetBob = new AppointmentBuilder().withName("Meet Bob").build();
        AddAppointmentCommand addMeetAlexCommand = new AddAppointmentCommand(meetAlex);
        AddAppointmentCommand addMeetBobCommand = new AddAppointmentCommand(meetBob);

        // same object -> returns true
        assertTrue(addMeetAlexCommand.equals(addMeetAlexCommand));

        // same values -> returns true
        AddAppointmentCommand addMeetAlexCommandCopy = new AddAppointmentCommand(meetAlex);
        assertTrue(addMeetAlexCommand.equals(addMeetAlexCommandCopy));

        // different types -> returns false
        assertFalse(addMeetAlexCommand.equals(1));

        // null -> returns false
        assertFalse(addMeetAlexCommand.equals(null));

        // different appointments -> returns false
        assertFalse(addMeetAlexCommand.equals(addMeetBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void undoAppointmentBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoPropertyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
            throw new AssertionError("This method should not be called.");
        }

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
        public Path getPropertyBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPropertyBook getPropertyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getPropertySize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProperty(Property target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Property getProperty(int i) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Property> getFilteredPropertyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(int i, Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(Property target, Property editedProperty) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPropertyList(Predicate<Property> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPropertyList(Comparator<Property> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointmentBook(ReadOnlyAppointmentBook newData) {
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
        public ReadOnlyAppointmentBook getAppointmentBook() {
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
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Appointment getAppointment(int i) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getAppointmentSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortAppointmentList(Comparator<Appointment> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single appointment.
     */
    private class ModelStubWithAppointment extends ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointment(appointment);
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }
    }
}
