package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.passenger.Passenger;

/**
 * A utility class to help with building Driver objects.
 */
public class PassengerSetBuilder {

    private Set<Passenger> passengers;

    /**
     * Creates a {@code PassengerBuilder} with no passengers.
     */
    public PassengerSetBuilder() {
        passengers = new HashSet<>();
    }

    /**
     * Initializes the PassengerBuilder with the data of {@code setToCopy}.
     */
    public PassengerSetBuilder(Set<Passenger> setToCopy) {
        passengers = setToCopy;
    }

    /**
     * Adds default passengers to the PassengerBuilder set
     * @return
     */
    public PassengerSetBuilder withDefaultPassengers() {
        passengers.add(TypicalPassengers.ALICE);
        passengers.add(TypicalPassengers.BOB);
        passengers.add(TypicalPassengers.CARL);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Driver} that we are building.
     */
    public PassengerSetBuilder withPassenger(Passenger passengerToAdd) {
        this.passengers.add(passengerToAdd);
        return this;
    }

    public Set<Passenger> build() {
        return this.passengers;
    }

}
