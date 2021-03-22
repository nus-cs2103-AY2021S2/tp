package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.testutil.PassengerBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPassenger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_passengerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPassengerAdded modelStub = new ModelStubAcceptingPassengerAdded();
        Passenger validPassenger = new PassengerBuilder().build();

        CommandResult commandResult = new AddCommand(validPassenger).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPassenger), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPassenger), modelStub.passengersAdded);
    }

    @Test
    public void execute_duplicatePassenger_throwsCommandException() {
        Passenger validPassenger = new PassengerBuilder().build();
        AddCommand addCommand = new AddCommand(validPassenger);
        ModelStub modelStub = new ModelStubWithPassenger(validPassenger);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_PASSENGER, () -> addCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Passenger alice = new PassengerBuilder().withName("Alice").build();
        Passenger bob = new PassengerBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different passenger -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public void addPassenger(Passenger passenger) {
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
        public boolean hasPassenger(Passenger passenger) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePassenger(Passenger passenger) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPassenger(Passenger target, Passenger editedPassenger) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Passenger> getFilteredPassengerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Passenger> getPassengerListByHasDriver() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Passenger> getFilteredPassengerListByDriver(Driver driver) {
            throw new AssertionError("This method should not be called.");
        }

        @Override

        public void updateFilteredPassengerList(Predicate<Passenger> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single passenger.
     */
    private class ModelStubWithPassenger extends ModelStub {
        private final Passenger passenger;

        ModelStubWithPassenger(Passenger passenger) {
            requireNonNull(passenger);
            this.passenger = passenger;
        }

        @Override
        public boolean hasPassenger(Passenger passenger) {
            requireNonNull(passenger);
            return this.passenger.isSamePassenger(passenger);
        }
    }

    /**
     * A Model stub that always accept the passenger being added.
     */
    private class ModelStubAcceptingPassengerAdded extends ModelStub {
        private final ArrayList<Passenger> passengersAdded = new ArrayList<>();

        @Override
        public boolean hasPassenger(Passenger passenger) {
            requireNonNull(passenger);
            return passengersAdded.stream().anyMatch(passenger::isSamePassenger);
        }

        @Override
        public void addPassenger(Passenger passenger) {
            requireNonNull(passenger);
            passengersAdded.add(passenger);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
