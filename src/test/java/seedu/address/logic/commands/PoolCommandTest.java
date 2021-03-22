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

class PoolCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullDriver_throwsNullPointerException() {
        Set<Index> commuters = new CommuterBuilder().build();
        assertThrows(NullPointerException.class, () -> new PoolCommand(null, commuters));
    }

    @Test
    public void constructor_nullCommuters_throwsNullPointerException() {
        Driver driver = new DriverBuilder().build();
        assertThrows(NullPointerException.class, () -> new PoolCommand(driver, null));
    }

    @Test
    public void equals_driver() {
        Driver driverAlice = new DriverBuilder().withName("Alice").build();
        Driver driverBob = new DriverBuilder().withName("Bob").build();
        Set<Index> commuters = new CommuterBuilder().build();

        PoolCommand poolAliceCommand = new PoolCommand(driverAlice, commuters);
        PoolCommand poolBobCommand = new PoolCommand(driverBob, commuters);

        // same object -> returns true
        assertEquals(poolAliceCommand, poolAliceCommand);

        // same values -> returns true
        PoolCommand poolAliceCommandCopy = new PoolCommand(driverAlice, commuters);
        assertEquals(poolAliceCommandCopy, poolAliceCommand);

        // different types -> returns false
        assertNotEquals(poolAliceCommand, 1);

        // null -> returns false
        assertNotEquals(poolAliceCommand, null);

        // different passenger -> returns false
        assertNotEquals(poolBobCommand, poolAliceCommand);
    }

    @Test
    public void equals_commuters() {
        Driver driver = new DriverBuilder().withName("Alice").build();
        Set<Index> commutersAlice = new CommuterBuilder().withIndices(new int[]{1, 2}).build();
        Set<Index> commutersBob = new CommuterBuilder().withIndices(new int[]{2, 3}).build();

        PoolCommand poolAliceCommand = new PoolCommand(driver, commutersAlice);
        PoolCommand poolBobCommand = new PoolCommand(driver, commutersBob);

        // same object -> returns true
        assertEquals(poolAliceCommand, poolAliceCommand);

        // same values -> returns true
        PoolCommand poolAliceCommandCopy = new PoolCommand(driver, commutersAlice);
        assertEquals(poolAliceCommandCopy, poolAliceCommand);

        // different passenger -> returns false
        assertNotEquals(poolBobCommand, poolAliceCommand);
    }

    @Test
    public void execute_singlePassengerUnfilteredList_success() {
        int index = 0;
        Driver driver = new DriverBuilder().build();
        Passenger editedPassenger = new PassengerBuilder(model.getFilteredPassengerList().get(index))
                .withDriver(driver).buildWithDriver();

        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{index + 1}).build());
        String expectedMessage = String.format(PoolCommand.MESSAGE_POOL_SUCCESS, driver, editedPassenger.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPassenger(model.getFilteredPassengerList().get(index), editedPassenger);

        assertCommandSuccess(poolCommand, model, expectedMessage, expectedModel);
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

        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder().withIndices(index).build());
        String expectedMessage = String.format(PoolCommand.MESSAGE_POOL_SUCCESS, driver, joiner.toString());

        assertCommandSuccess(poolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_seqPassengerUnfilteredList_success() {
        int[] index = {1, 3, 4};

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Driver driver = new DriverBuilder().build();

        // Form the message from the last passengers name
        String expectedMessage = String.format(PoolCommand.MESSAGE_POOL_SUCCESS, driver,
                model.getFilteredPassengerList().get(index[index.length - 1] - 1).getName());

        for (int idx : index) {
            Passenger editedPassenger = new PassengerBuilder(model.getFilteredPassengerList().get(idx - 1))
                    .withDriver(driver).buildWithDriver();
            expectedModel.setPassenger(model.getFilteredPassengerList().get(idx - 1), editedPassenger);
        }

        try {
            for (int i = 0; i < index.length - 1; i++) {
                PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder().withIndices(
                        new int[]{index[i]}).build());
                poolCommand.execute(model);
            }
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        // Form the final command
        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder().withIndices(
                new int[]{index[index.length - 1]}).build());

        assertCommandSuccess(poolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPassengerAtIndex(model, INDEX_FIRST_PASSENGER);
        Passenger passengerInFilteredList = model.getFilteredPassengerList().get(INDEX_FIRST_PASSENGER.getZeroBased());

        Driver driver = new DriverBuilder().build();
        Passenger editedPassenger = new PassengerBuilder(passengerInFilteredList)
                .withDriver(driver).buildWithDriver();

        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{INDEX_FIRST_PASSENGER.getOneBased()}).build());
        String expectedMessage = String.format(PoolCommand.MESSAGE_POOL_SUCCESS, driver, editedPassenger.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPassenger(model.getFilteredPassengerList().get(INDEX_FIRST_PASSENGER.getZeroBased()),
                editedPassenger);

        assertCommandSuccess(poolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptySet_failure() {
        Driver driver = new DriverBuilder().build();
        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder().withIndices(new int[]{}).build());

        assertCommandFailure(poolCommand, model, PoolCommand.MESSAGE_NO_COMMUTERS);
    }

    @Test
    public void execute_invalidPassengerIndexUnfilteredList_failure() {
        int outOfBoundIndex = model.getFilteredPassengerList().size() + 1;
        Driver driver = new DriverBuilder().build();

        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{outOfBoundIndex}).build());

        assertCommandFailure(poolCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPassengerIndexFilteredList_failure() {
        showPassengerAtIndex(model, INDEX_FIRST_PASSENGER);
        int outOfBoundIndex = INDEX_SECOND_PASSENGER.getOneBased();
        Driver driver = new DriverBuilder().build();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex - 1 < model.getAddressBook().getPassengerList().size());

        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{outOfBoundIndex}).build());

        assertCommandFailure(poolCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }
}
