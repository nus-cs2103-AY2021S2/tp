package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPassengerAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PASSENGER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PASSENGER;
import static seedu.address.testutil.TypicalPassengers.HILARY;
import static seedu.address.testutil.TypicalPassengers.HILARY_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.IRENE;
import static seedu.address.testutil.TypicalPassengers.IRENE_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.JACKSON;
import static seedu.address.testutil.TypicalPassengers.JACKSON_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.KINGSLEY;
import static seedu.address.testutil.TypicalPassengers.KINGSLEY_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.IsSpecifiedDriverPredicate;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.PassengerBuilder;

class UnpoolCommandTest {

    @Test
    public void constructor_nullDriver_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnpoolCommand(null));
    }

    @Test
    public void equals_driver() {
        Driver driverAlice = new DriverBuilder().withName("Alice").build();
        Driver driverBob = new DriverBuilder().withName("Bob").build();

        UnpoolCommand unpoolAliceCommand = new UnpoolCommand(driverAlice);
        UnpoolCommand unpoolBobCommand = new UnpoolCommand(driverBob);

        // same object -> returns true
        assertEquals(unpoolAliceCommand, unpoolAliceCommand);

        // same values -> returns true
        UnpoolCommand poolAliceCommandCopy = new UnpoolCommand(driverAlice);
        assertEquals(poolAliceCommandCopy, unpoolAliceCommand);

        // different types -> returns false
        assertNotEquals(unpoolAliceCommand, 1);

        // null -> returns false
        assertNotEquals(unpoolAliceCommand, null);

        // different passenger -> returns false
        assertNotEquals(unpoolBobCommand, unpoolAliceCommand);
    }

    @Test
    public void execute_singlePassengerUnfilteredList_success() throws Exception {
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder(getTypicalAddressBook())
                .withPassenger(HILARY);
        Model model = new ModelManager(addressBookBuilder.build(), new UserPrefs());

        AddressBookBuilder expectedAddressBookBuilder = new AddressBookBuilder(getTypicalAddressBook())
                .withPassenger(HILARY_NO_DRIVER);
        Model expectedModel = new ModelManager(expectedAddressBookBuilder.build(), new UserPrefs());

        Driver driver = new DriverBuilder().build();
        UnpoolCommand unpoolCommand = new UnpoolCommand(driver);
        String expectedMessage = String.format(UnpoolCommand.MESSAGE_UNPOOL_SUCCESS, driver, HILARY.getName());

        assertCommandSuccess(unpoolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiPassengerUnfilteredList_success() {
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder(getTypicalAddressBook())
                .withPassenger(HILARY).withPassenger(IRENE).withPassenger(JACKSON).withPassenger(KINGSLEY);
        Model model = new ModelManager(addressBookBuilder.build(), new UserPrefs());

        AddressBookBuilder expectedAddressBookBuilder = new AddressBookBuilder(getTypicalAddressBook())
                .withPassenger(HILARY_NO_DRIVER).withPassenger(IRENE_NO_DRIVER)
                .withPassenger(JACKSON_NO_DRIVER).withPassenger(KINGSLEY_NO_DRIVER);
        Model expectedModel = new ModelManager(expectedAddressBookBuilder.build(), new UserPrefs());

        Driver driver = new DriverBuilder().build();
        UnpoolCommand unpoolCommand = new UnpoolCommand(driver);
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add(HILARY.getName().toString()).add(IRENE.getName().toString())
                .add(JACKSON.getName().toString()).add(KINGSLEY.getName().toString());
        String expectedMessage = String.format(UnpoolCommand.MESSAGE_UNPOOL_SUCCESS, driver, joiner);

        assertCommandSuccess(unpoolCommand, model, expectedMessage, expectedModel);
    }

    // TODO: write a filtered list success test

    @Test
    public void execute_noExistingDriverNameUnfilteredList_failure() {
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder(getTypicalAddressBook())
                .withPassenger(HILARY).withPassenger(IRENE).withPassenger(JACKSON).withPassenger(KINGSLEY);
        Model model = new ModelManager(addressBookBuilder.build(), new UserPrefs());

        Driver driver = new DriverBuilder().withName("Mike Hunt").build();
        UnpoolCommand unpoolCommand = new UnpoolCommand(driver);

        assertCommandFailure(unpoolCommand, model, UnpoolCommand.MESSAGE_DRIVER_NOT_EXIST);
    }

//    @Test
//    public void execute_invalidPassengerIndexFilteredList_failure() {
//        showPassengerAtIndex(model, INDEX_FIRST_PASSENGER);
//        int outOfBoundIndex = INDEX_SECOND_PASSENGER.getOneBased();
//        Driver driver = new DriverBuilder().build();
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex - 1 < model.getAddressBook().getPassengerList().size());
//
//        PoolCommand poolCommand = new PoolCommand(driver, new CommuterBuilder()
//                .withIndices(new int[]{outOfBoundIndex}).build());
//
//        assertCommandFailure(poolCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
//    }
}

