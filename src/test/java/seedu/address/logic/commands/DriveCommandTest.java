package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.human.driver.Driver;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;

class DriveCommandTest {

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

        // different person -> returns false
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

        // different person -> returns false
        assertNotEquals(driveBobCommand, driveAliceCommand);
    }
}
