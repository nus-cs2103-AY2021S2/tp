package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPassengers.HILARY;
import static seedu.address.testutil.TypicalPassengers.HILARY_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.IRENE;
import static seedu.address.testutil.TypicalPassengers.IRENE_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.JACKSON;
import static seedu.address.testutil.TypicalPassengers.JACKSON_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.KINGSLEY;
import static seedu.address.testutil.TypicalPassengers.KINGSLEY_NO_DRIVER;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBook;

import java.util.StringJoiner;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.driver.Driver;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.DriverBuilder;

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

    // TODO: write a filtered list execute success test

    @Test
    public void execute_noExistingDriverNameUnfilteredList_failure() {
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder(getTypicalAddressBook())
                .withPassenger(HILARY).withPassenger(IRENE).withPassenger(JACKSON).withPassenger(KINGSLEY);
        Model model = new ModelManager(addressBookBuilder.build(), new UserPrefs());

        Driver driver = new DriverBuilder().withName("Mike Hunt").build();
        UnpoolCommand unpoolCommand = new UnpoolCommand(driver);

        assertCommandFailure(unpoolCommand, model, UnpoolCommand.MESSAGE_DRIVER_NOT_EXIST);
    }

    // TODO: write a filtered list no existing driver failure test

}

