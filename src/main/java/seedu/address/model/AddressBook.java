package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.UniquePassengerList;
import seedu.address.model.pool.Pool;
import seedu.address.model.pool.UniquePoolList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePassenger comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePassengerList passengers;
    private final UniquePoolList pools;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        passengers = new UniquePassengerList();
        pools = new UniquePoolList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Passengers in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the passenger list with {@code passengers}.
     * {@code passengers} must not contain duplicate passengers.
     */
    public void setPassengers(List<Passenger> passengers) {
        this.passengers.setPassengers(passengers);
    }

    /**
     * Replaces the contents of the pool list with {@code pool}.
     * {@code pool} must not contain duplicate pool.
     */
    public void setPools(List<Pool> pool) {
        this.pools.setPools(pool);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPassengers(newData.getPassengerList());
        setPools(newData.getPoolList());
    }

    //// passenger-level operations

    /**
     * Returns true if a passenger with the same identity as {@code passenger} exists in the address book.
     */
    public boolean hasPassenger(Passenger passenger) {
        requireNonNull(passenger);
        return passengers.contains(passenger);
    }

    /**
     * Adds a passenger to the address book.
     * The passenger must not already exist in the address book.
     */
    public void addPassenger(Passenger p) {
        passengers.add(p);
    }

    /**
     * Replaces the given passenger {@code target} in the list with {@code editedPassenger}.
     * {@code target} must exist in the address book.
     * The passenger identity of {@code editedPassenger} must not be the same as another existing passenger in the
     * address book.
     */
    public void setPassenger(Passenger target, Passenger editedPassenger) {
        requireNonNull(editedPassenger);

        passengers.setPassenger(target, editedPassenger);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePassenger(Passenger key) {
        passengers.remove(key);
    }


    ////Pool level operations

    /**
     * Returns true if a passenger with the same identity as {@code passenger} exists in the address book.
     */
    public boolean hasPool(Pool pool) {
        requireNonNull(pool);
        return pools.contains(pool);
    }

    /**
     * Adds a passenger to the address book.
     * The passenger must not already exist in the address book.
     */
    public void addPool(Pool p) {
        pools.add(p);
    }

    /**
     * Replaces the given passenger {@code target} in the list with {@code editedPassenger}.
     * {@code target} must exist in the address book.
     * The passenger identity of {@code editedPassenger} must not be the same as another existing passenger in the
     * address book.
     */
    public void setPool(Pool target, Pool editedPool) {
        requireNonNull(editedPool);

        pools.setPool(target, editedPool);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePool(Pool key) {
        pools.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return passengers.asUnmodifiableObservableList().size() + " passengers; "
                + pools.asUnmodifiableObservableList().size() + " pools";
        // TODO: refine later
    }

    @Override
    public ObservableList<Passenger> getPassengerList() {
        return passengers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Pool> getPoolList() {
        return pools.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && passengers.equals(((AddressBook) other).passengers)
                && pools.equals(((AddressBook) other).pools));
    }

    @Override
    public int hashCode() {
        //TODO might need to change if got collisions
        return Objects.hash(passengers, pools);
    }
}
