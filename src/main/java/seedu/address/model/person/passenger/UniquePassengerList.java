package seedu.address.model.person.passenger;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.passenger.exceptions.DuplicatePassengerException;
import seedu.address.model.person.passenger.exceptions.PassengerNotFoundException;

/**
 * A list of passengers that enforces uniqueness between its elements and does not allow nulls.
 * A passenger is considered unique by comparing using {@code Passenger#isSamePassenger(Passenger)}. As such, adding and
 * updating of passenger uses Passenger#isSamePassenger(Passenger) for equality so as to ensure that the passenger being
 * added or updated is unique in terms of identity in the UniquePassengerList. However, the removal of a passenger uses
 * Passenger#equals(Object) so as to ensure that the passenger with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Passenger#isSamePassenger(Passenger)
 */
public class UniquePassengerList implements Iterable<Passenger> {

    private final ObservableList<Passenger> internalList = FXCollections.observableArrayList();
    private final ObservableList<Passenger> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent passenger as the given argument.
     */
    public boolean contains(Passenger toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePassenger);
    }

    /**
     * Returns true if the list contains an equal passenger as the given argument.
     */
    public boolean containsEqual(Passenger toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a passenger to the list.
     * The passenger must not already exist in the list.
     */
    public void add(Passenger toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePassengerException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the passenger {@code target} in the list with {@code editedPassenger}.
     * {@code target} must exist in the list.
     * The passenger identity of {@code editedPassenger} must not be the same as another existing passenger in the list.
     */
    public void setPassenger(Passenger target, Passenger editedPassenger) {
        requireAllNonNull(target, editedPassenger);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PassengerNotFoundException();
        }

        if (!target.isSamePassenger(editedPassenger) && contains(editedPassenger)) {
            throw new DuplicatePassengerException();
        }

        internalList.set(index, editedPassenger);
    }

    /**
     * Removes the equivalent passenger from the list.
     * The passenger must exist in the list.
     */
    public void remove(Passenger toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PassengerNotFoundException();
        }
    }

    public void setPassengers(UniquePassengerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code passengers}.
     * {@code passengers} must not contain duplicate passengers.
     */
    public void setPassengers(List<Passenger> passengers) {
        requireAllNonNull(passengers);
        if (!passengersAreUnique(passengers)) {
            throw new DuplicatePassengerException();
        }

        internalList.setAll(passengers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Passenger> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Passenger> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePassengerList // instanceof handles nulls
                        && internalList.equals(((UniquePassengerList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code passengers} contains only unique passengers.
     */
    private boolean passengersAreUnique(List<Passenger> passengers) {
        for (int i = 0; i < passengers.size() - 1; i++) {
            for (int j = i + 1; j < passengers.size(); j++) {
                if (passengers.get(i).isSamePassenger(passengers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
