package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the passengers list.
     * This list will not contain any duplicate passengers.
     */
    ObservableList<Passenger> getPassengerList();

    /**
     * Returns an unmodifiable view of the pools list.
     * This list will not contain any duplicate passengers.
     */
    ObservableList<Pool> getPoolList();

}
