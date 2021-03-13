package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;
import java.util.StringJoiner;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.human.driver.Driver;
import seedu.address.model.human.person.Person;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

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

    @Test
    public void execute_singlePersonUnfilteredList_success() {
        int index = 0;
        Driver driver = new DriverBuilder().build();
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(index))
                .withDriver(driver).buildWithDriver();

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder().withIndices(new int[]{index + 1}).build());
        String expectedMessage = String.format(DriveCommand.MESSAGE_DRIVE_SUCCESS, driver, editedPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(index), editedPerson);

        assertCommandSuccess(driveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multiPersonUnfilteredList_success() {
        int[] index = {1, 3, 4};
        StringJoiner joiner = new StringJoiner(", ");

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        Driver driver = new DriverBuilder().build();
        for (int idx : index) {
            Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(idx - 1))
                    .withDriver(driver).buildWithDriver();
            joiner.add(editedPerson.getName().toString());
            expectedModel.setPerson(model.getFilteredPersonList().get(idx - 1), editedPerson);
        }

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder().withIndices(index).build());
        String expectedMessage = String.format(DriveCommand.MESSAGE_DRIVE_SUCCESS, driver, joiner.toString());

        assertCommandSuccess(driveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Driver driver = new DriverBuilder().build();
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .withDriver(driver).buildWithDriver();

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{INDEX_FIRST_PERSON.getOneBased()}).build());
        String expectedMessage = String.format(DriveCommand.MESSAGE_DRIVE_SUCCESS, driver, editedPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPerson);

        assertCommandSuccess(driveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptySet_failure() {
        Driver driver = new DriverBuilder().build();
        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder().withIndices(new int[]{}).build());

        assertCommandFailure(driveCommand, model, DriveCommand.MESSAGE_NO_COMMUTERS);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        int outOfBoundIndex = model.getFilteredPersonList().size() + 1;
        Driver driver = new DriverBuilder().build();

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{outOfBoundIndex}).build());

        assertCommandFailure(driveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        int outOfBoundIndex = INDEX_SECOND_PERSON.getOneBased();
        Driver driver = new DriverBuilder().build();
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex - 1 < model.getAddressBook().getPersonList().size());

        DriveCommand driveCommand = new DriveCommand(driver, new CommuterBuilder()
                .withIndices(new int[]{outOfBoundIndex}).build());

        assertCommandFailure(driveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
