package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.passenger.Passenger;

/**
 * A utility class to help with building Driver objects.
 */
public class PassengerListBuilder {

    private List<Passenger> passengers;

    /**
     * Creates a {@code PassengerBuilder} with no passengers.
     */
    public PassengerListBuilder() {
        passengers = new ArrayList<>();
    }

    /**
     * Initializes the PassengerBuilder with the data of {@code setToCopy}.
     */
    public PassengerListBuilder(List<Passenger> setToCopy) {
        passengers = setToCopy;
    }

    /**
     * Adds default passengers to the PassengerBuilder set.
     * @return a {@link PassengerListBuilder} with the default passengers.
     */
    public PassengerListBuilder withDefaultPassengers() {
        passengers.add(TypicalPassengers.ALICE);
        passengers.add(TypicalPassengers.BOB);
        passengers.add(TypicalPassengers.CARL);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Driver} that we are building.
     */
    public PassengerListBuilder withPassenger(Passenger passengerToAdd) {
        this.passengers.add(passengerToAdd);
        return this;
    }

    public List<Passenger> build() {
        return this.passengers;
    }

}
