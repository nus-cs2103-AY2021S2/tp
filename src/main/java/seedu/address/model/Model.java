package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Passenger> PREDICATE_SHOW_ALL_PASSENGERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Pool> PREDICATE_SHOW_ALL_POOLS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a passenger with the same identity as {@code passenger} exists in the address book.
     */
    boolean hasPassenger(Passenger passenger);

    /**
     * Returns true if a pool with the same identity as {@code pool} exists in the address book.
     */
    boolean hasPool(Pool pool);

    /**
     * Returns true if a pool that contains the given {@code passenger} exists in the address book.
     */
    boolean hasPoolWithPassenger(Passenger passenger);

    /**
     * Deletes the given passenger.
     * The passenger must exist in the address book.
     */
    void deletePassenger(Passenger passenger);

    /**
     * Deletes the given pool.
     * The pool must exist in the address book.
     */
    void deletePool(Pool pool);

    /**
     * Adds the given passenger.
     * {@code passenger} must not already exist in the address book.
     */
    void addPassenger(Passenger passenger);

    /**
     * Adds the given pool.
     * {@code pool} must not already exist in the address book.
     */
    void addPool(Pool pool);

    /**
     * Replaces the given passenger {@code target} with {@code editedPassenger}.
     * {@code target} must exist in the address book.
     * The passenger identity of {@code editedPassenger} must not be the same as another existing passenger in the
     * address book.
     */
    void setPassenger(Passenger target, Passenger editedPassenger);

    /** Returns an unmodifiable view of the filtered passenger list */
    ObservableList<Passenger> getFilteredPassengerList();

    /**
     * Updates the filter of the filtered passenger list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPassengerList(Predicate<Passenger> predicate);

    /** Returns an unmodifiable view of the filtered pool list */
    ObservableList<Pool> getFilteredPoolList();

    /**
     * Updates the filter of the filtered pool list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPoolList(Predicate<Pool> predicate);
}
