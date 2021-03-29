package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPDAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRIPTIME_MORNING;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.pool.TripDay;
import seedu.address.model.pool.TripTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;

public class PoolCommandTest {

    private final Driver driver = new DriverBuilder().build();
    private final Set<Index> commuters = new CommuterBuilder().build();
    private final TripDay tripDay = new TripDay(VALID_TRIPDAY_MONDAY);
    private final TripTime tripTime = new TripTime(VALID_TRIPTIME_MORNING);
    private final Set<Tag> tags = new HashSet<>();

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PoolCommand(null, null, null,
            null, null));
    }

    // TODO to fix test cases
    //    @Test
    //    public void execute_passengerAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingPoolAdded modelStub = new ModelStubAcceptingPoolAdded();
    //        Pool validPool = new PoolBuilder().build();
    //
    //        CommandResult commandResult = new PoolCommand(driver,
    //        commuters, tripDay, tripTime, tags).execute(modelStub);
    //
    //        assertEquals(String.format(PoolCommand.MESSAGE_POOL_SUCCESS, validPool),
    //        commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validPool), modelStub.poolsAdded);
    //    }

    //    @Test
    //    public void execute_duplicatePassenger_throwsCommandException() {
    //        Passenger validPassenger = new PassengerBuilder().build();
    //        AddCommand addCommand = new PoolCommand(validPassenger);
    //        ModelStub modelStub = new ModelStubWithPassenger(validPassenger);
    //
    //        assertThrows(CommandException.class,
    //                AddCommand.MESSAGE_DUPLICATE_PASSENGER, () -> addCommand.execute(modelStub)
    //        );
    //    }
    //
    //    @Test
    //    public void equals() {
    //        Passenger alice = new PassengerBuilder().withName("Alice").build();
    //        Passenger bob = new PassengerBuilder().withName("Bob").build();
    //        AddCommand addAliceCommand = new PoolCommand(alice);
    //        AddCommand addBobCommand = new PoolCommand(bob);
    //
    //        // same object -> returns true
    //        assertTrue(addAliceCommand.equals(addAliceCommand));
    //
    //        // same values -> returns true
    //        AddCommand addAliceCommandCopy = new PoolCommand(alice);
    //        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
    //
    //        // different types -> returns false
    //        assertFalse(addAliceCommand.equals(1));
    //
    //        // null -> returns false
    //        assertFalse(addAliceCommand.equals(null));
    //
    //        // different passenger -> returns false
    //        assertFalse(addAliceCommand.equals(addBobCommand));
    //    }
    //
    //    /**
    //     * A default model stub that have all of the methods failing.
    //     */
    //    private class ModelStub implements Model {
    //        @Override
    //        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public ReadOnlyUserPrefs getUserPrefs() {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public GuiSettings getGuiSettings() {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void setGuiSettings(GuiSettings guiSettings) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public Path getAddressBookFilePath() {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void setAddressBookFilePath(Path addressBookFilePath) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void addPassenger(Passenger passenger) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void addPool(Pool pool) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void setAddressBook(ReadOnlyAddressBook newData) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public ReadOnlyAddressBook getAddressBook() {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public boolean hasPassenger(Passenger passenger) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public boolean hasPool(Pool pool) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void deletePassenger(Passenger passenger) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void deletePool(Pool pool) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public void setPassenger(Passenger target, Passenger editedPassenger) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //        public ObservableList<Passenger> getFilteredPassengerList() {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //
    //        @Override
    //
    //        public void updateFilteredPassengerList(Predicate<Passenger> predicate) {
    //            throw new AssertionError("This method should not be called.");
    //        }
    //    }
    //
    //    /**
    //     * A Model stub that contains a single pool.
    //     */
    //    private class ModelStubWithPool extends ModelStub {
    //        private final Pool pool;
    //
    //        ModelStubWithPool(Pool pool) {
    //            requireNonNull(pool);
    //            this.pool = pool;
    //        }
    //
    //        @Override
    //        public boolean hasPool(Pool pool) {
    //            requireNonNull(pool);
    //            return this.pool.isSamePool(pool);
    //        }
    //    }
    //
    //    /**
    //     * A Model stub that always accept the passenger being added.
    //     */
    //    private class ModelStubAcceptingPoolAdded extends ModelStub {
    //        private final ArrayList<Pool> poolsAdded = new ArrayList<>();
    //
    //        @Override
    //        public boolean hasPool(Pool pool) {
    //            requireNonNull(pool);
    //            return poolsAdded.stream().anyMatch(pool::isSamePool);
    //        }
    //
    //        @Override
    //        public void addPool(Pool pool) {
    //            requireNonNull(pool);
    //            poolsAdded.add(pool);
    //        }
    //
    //        @Override
    //        public ReadOnlyAddressBook getAddressBook() {
    //            return new AddressBook();
    //        }
    //
    //        @Override
    //        public ObservableList<Passenger> getFilteredPassengerList() {
    //            return poolsAdded;
    //        }
    //    }

}
