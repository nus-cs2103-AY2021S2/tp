package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPassengerAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PASSENGER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PASSENGER;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBook;

import java.util.Set;
import java.util.StringJoiner;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.PassengerBuilder;

class DriveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullDriver_throwsNullPointerException() {
        Set<Index> commuters = new CommuterBuilder().build();
        assertThrows(NullPointerException.class, () -> new DriveCommand(null, commuters));
    }

    @Test
    public void constructor_nullCommuters_throwsNullPointerException() {
        Driver driver = new DriverBuilder().build();
        assertThrows(NullPointerException.class, () -> new DriveCommand(driver, null));
    }

    @Test
    public void equals_driver() {
        Driver driverAlice = new DriverBuilder().withName("Alice").build();
        Driver driverBob = new DriverBuilder().withName("Bob").build();
        Set<Index> commuters = new CommuterBuilder().build();

        DriveCommand driveAliceCommand = new DriveCommand(driverAlice, commuters);
        DriveCommand driveBobCommand = new DriveCommand(driverBob, commuters);

        // same object -> returns true
        assertEquals(driveAliceCommand, driveAliceCommand);

        // same values -> returns true
        DriveCommand driveAliceCommandCopy = new DriveCommand(driverAlice, commuters);
        assertEquals(driveAliceCommandCopy, driveAliceCommand);

        // different types -> returns false
        assertNotEquals(driveAliceCommand, 1);

        // null -> returns false
        assertNotEquals(driveAliceCommand, null);

        // different passenger -> returns false
        assertNotEquals(driveBobCommand, driveAliceCommand);
    }

    @Test
    public void equals_commuters() {
        Driver driver = new DriverBuilder().withName("Alice").build();
        Set<Index> commutersAlice = new CommuterBuilder().withIndices(new int[]{1, 2}).build();
        Set<Index> commutersBob = new CommuterBuilder().withIndices(new int[]{2, 3}).build();

        DriveCommand driveAliceCommand = new DriveCommand(driver, commutersAlice);
        DriveCommand driveBobCommand = new DriveCommand(driver, commutersBob);

        // same object -> returns true
        assertEquals(driveAliceCommand, driveAliceCommand);

        // same values -> returns true
        DriveCommand driveAliceCommandCopy = new DriveCommand(driver, commutersAlice);
        assertEquals(driveAliceCommandCopy, driveAliceCommand);

        // different passenger -> returns false
        assertNotEquals(driveBobCommand, driveAliceCommand);
    }

    @Test
    public void execute_singlePassengerUnfilteredList_success() {
        int index = 0;
        Driver driver = new DriverBuilder().build();
        Passenger editedPassenger = new PassengerBuilder(model.getFilteredPassengerList().get(index))
                .withDriver(driver).buildWithDriver();

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{index + 1}).build());
        String expectedMessage = String.format(DriveCommand.MESSAGE_DRIVE_SUCCESS, driver, editedPassenger.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPassenger(model.getFilteredPassengerList().get(index), editedPassenger);

        assertCommandSuccess(driveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiPassengerUnfilteredList_success() {
        int[] index = {1, 3, 4};
        StringJoiner joiner = new StringJoiner(", ");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Driver driver = new DriverBuilder().build();
        for (int idx : index) {
            Passenger editedPassenger = new PassengerBuilder(model.getFilteredPassengerList().get(idx - 1))
                    .withDriver(driver).buildWithDriver();
            joiner.add(editedPassenger.getName().toString());
            expectedModel.setPassenger(model.getFilteredPassengerList().get(idx - 1), editedPassenger);
        }

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder().withIndices(index).build());
        String expectedMessage = String.format(DriveCommand.MESSAGE_DRIVE_SUCCESS, driver, joiner.toString());

        assertCommandSuccess(driveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_seqPassengerUnfilteredList_success() {
        int[] index = {1, 3, 4};
        StringJoiner joiner = new StringJoiner(", ");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Driver driver = new DriverBuilder().build();

        // Form the message from the last passengers name
        String expectedMessage = String.format(DriveCommand.MESSAGE_DRIVE_SUCCESS, driver,
                model.getFilteredPassengerList().get(index[index.length - 1] - 1).getName());

        for (int idx : index) {
            Passenger editedPassenger = new PassengerBuilder(model.getFilteredPassengerList().get(idx - 1))
                    .withDriver(driver).buildWithDriver();
            joiner.add(editedPassenger.getName().toString());
            expectedModel.setPassenger(model.getFilteredPassengerList().get(idx - 1), editedPassenger);
        }

        // Execute the commands sequentially, index[i] - i to account for filter shifting
        try {
            for (int i = 0; i < index.length - 1; i++) {
                DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder().withIndices(
                        new int[]{index[i] - i}).build());
                driveCommand.execute(model);
            }
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        // Form the final command
        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder().withIndices(
                new int[]{index[index.length - 1] - index.length + 1}).build());

        assertCommandSuccess(driveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPassengerAtIndex(model, INDEX_FIRST_PASSENGER);
        Passenger passengerInFilteredList = model.getFilteredPassengerList().get(INDEX_FIRST_PASSENGER.getZeroBased());

        Driver driver = new DriverBuilder().build();
        Passenger editedPassenger = new PassengerBuilder(passengerInFilteredList)
                .withDriver(driver).buildWithDriver();

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{INDEX_FIRST_PASSENGER.getOneBased()}).build());
        String expectedMessage = String.format(DriveCommand.MESSAGE_DRIVE_SUCCESS, driver, editedPassenger.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPassenger(model.getFilteredPassengerList().get(INDEX_FIRST_PASSENGER.getZeroBased()),
                editedPassenger);

        assertCommandSuccess(driveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptySet_failure() {
        Driver driver = new DriverBuilder().build();
        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder().withIndices(new int[]{}).build());

        assertCommandFailure(driveCommand, model, DriveCommand.MESSAGE_NO_COMMUTERS);
    }

    @Test
    public void execute_invalidPassengerIndexUnfilteredList_failure() {
        int outOfBoundIndex = model.getFilteredPassengerList().size() + 1;
        Driver driver = new DriverBuilder().build();

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{outOfBoundIndex}).build());

        assertCommandFailure(driveCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPassengerIndexFilteredList_failure() {
        showPassengerAtIndex(model, INDEX_FIRST_PASSENGER);
        int outOfBoundIndex = INDEX_SECOND_PASSENGER.getOneBased();
        Driver driver = new DriverBuilder().build();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex - 1 < model.getAddressBook().getPassengerList().size());

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{outOfBoundIndex}).build());

        assertCommandFailure(driveCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }
}
