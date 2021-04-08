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
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.AddressBookSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.contact.Contact;
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
    public void execute_duplicateContact_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder().build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class, AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, (
                ) -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment ptm = new AppointmentBuilder().withName("PTM").build();
        Appointment playDate = new AppointmentBuilder().withName("Play Date").build();
        AddAppointmentCommand addPtmCommand = new AddAppointmentCommand(ptm);
        AddAppointmentCommand addPlayDateCommand = new AddAppointmentCommand(playDate);

        // same object -> returns true
        assertTrue(addPtmCommand.equals(addPtmCommand));

        // same values -> returns true
        AddAppointmentCommand addPtmCommandCopy = new AddAppointmentCommand(ptm);
        assertTrue(addPtmCommand.equals(addPtmCommandCopy));

        // different types -> returns false
        assertFalse(addPtmCommand.equals(1));

        // null -> returns false
        assertFalse(addPtmCommand.equals(null));

        // different contact -> returns false
        assertFalse(addPtmCommand.equals(addPlayDateCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        //=========== UserPrefs ==================================================================================

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
        public String getTheme() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTheme(String theme) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public AddressBookSettings getAddressBookSettings() {
            throw new AssertionError("This mmethod should not be called.");
        }

        @Override
        public void setAddressBookSettings(AddressBookSettings addressBookSettings) {
            throw new AssertionError("This mmethod should not be called.");
        }

        @Override
        public Comparator<Contact> getAddressBookComparator() {
            throw new AssertionError("This mmethod should not be called.");
        }

        @Override
        public void setAddressBookComparator(String comparator) {
            throw new AssertionError("This mmethod should not be called.");
        }

        //=========== AddressBook ================================================================================

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContacts(List<Contact> contacts) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortContactList(String comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void orderContacts() {
            throw new AssertionError("This method should not be called.");
        }

        //=========== AppointmentBook ================================================================================

        @Override
        public Path getAppointmentBookFilePath() {
            throw new AssertionError("This method should not be called.");
        };

        public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
            throw new AssertionError("This method should not be called.");
        };

        public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
            throw new AssertionError("This method should not be called.");
        };

        public ReadOnlyAppointmentBook getAppointmentBook() {
            throw new AssertionError("This method should not be called.");
        };

        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        };

        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        };

        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        };

        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        };

        public void setAppointments(List<Appointment> appointments) {
            throw new AssertionError("This method should not be called.");
        }

        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        };

        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void orderAppointments() {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single contact.
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
     * A Model stub that always accept the contact being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyAppointmentBook getAppointmentBook() {
            return new AppointmentBook();
        }
    }
}
