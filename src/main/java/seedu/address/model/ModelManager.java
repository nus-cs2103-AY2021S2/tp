package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Passenger> filteredPassengers;
    private final FilteredList<Pool> filteredPools;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPassengers = new FilteredList<>(this.addressBook.getPassengerList());
        this.filteredPools = new FilteredList<>(this.addressBook.getPoolList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPassenger(Passenger passenger) {
        requireNonNull(passenger);
        return addressBook.hasPassenger(passenger);
    }

    @Override
    public boolean hasPool(Pool pool) {
        requireNonNull(pool);
        return addressBook.hasPool(pool);
    }

    @Override
    public boolean hasPoolWithPassenger(Passenger passenger) {
        requireNonNull(passenger);
        return addressBook.hasPoolWithPassenger(passenger);
    }

    @Override
    public void deletePassenger(Passenger passenger) {
        requireNonNull(passenger);
        addressBook.removePassenger(passenger);
    }

    @Override
    public void deletePool(Pool pool) {
        addressBook.removePool(pool);
    }

    @Override
    public void addPassenger(Passenger passenger) {
        addressBook.addPassenger(passenger);
        updateFilteredPassengerList(PREDICATE_SHOW_ALL_PASSENGERS);
    }

    @Override
    public void setPassenger(Passenger target, Passenger editedPassenger) {
        requireAllNonNull(target, editedPassenger);

        addressBook.setPassenger(target, editedPassenger);
    }

    //=========== Filtered Passenger List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Passenger} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Passenger> getFilteredPassengerList() {
        return filteredPassengers;
    }

    @Override
    public void updateFilteredPassengerList(Predicate<Passenger> predicate) {
        requireNonNull(predicate);
        filteredPassengers.setPredicate(predicate);
    }

    //=========== Filtered Pool List Accessors =============================================================

    @Override
    public void addPool(Pool pool) {
        addressBook.addPool(pool);
        updateFilteredPoolList(PREDICATE_SHOW_ALL_POOLS);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Pool} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Pool> getFilteredPoolList() {
        return filteredPools;
    }

    @Override
    public void updateFilteredPoolList(Predicate<Pool> predicate) {
        requireNonNull(predicate);
        filteredPools.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPassengers.equals(other.filteredPassengers)
                && filteredPools.equals(other.filteredPools);
    }

}
