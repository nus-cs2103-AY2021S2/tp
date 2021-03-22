package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Passenger;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Passenger> PREDICATE_SHOW_ALL_PASSENGERS = unused -> true;

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
     * Deletes the given passenger.
     * The passenger must exist in the address book.
     */
    void deletePassenger(Passenger passenger);

    /**
     * Adds the given passenger.
     * {@code passenger} must not already exist in the address book.
     */
    void addPassenger(Passenger passenger);

    /**
     * Replaces the given passenger {@code target} with {@code editedPassenger}.
     * {@code target} must exist in the address book.
     * The passenger identity of {@code editedPassenger} must not be the same as another existing passenger in the
     * address book.
     */
    void setPassenger(Passenger target, Passenger editedPassenger);

    /** Returns an unmodifiable view of the filtered passenger list */
    ObservableList<Passenger> getFilteredPassengerList();

    /** Returns an unmodifiable view of the filtered passenger list if
     * the passenger is assigned to a driver already */
    ObservableList<Passenger> getPassengerListByHasDriver();

    /** Returns an unmodifiable view of the filtered passenger list if
     * the passenger contains the driver specified */
    ObservableList<Passenger> getFilteredPassengerListByDriver(Driver driver);

    /**
     * Updates the filter of the filtered passenger list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPassengerList(Predicate<Passenger> predicate);
}
