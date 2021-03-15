package seedu.address.model.human.person;

import java.util.function.Predicate;

import seedu.address.model.person.passenger.Passenger;

/**
 * Tests that whether a {@code Passenger} is assigned {@code Driver} matches the status given.
 */
public class IsAssignedDriverPredicate implements Predicate<Passenger> {
    private final boolean isAssigned;

    public IsAssignedDriverPredicate(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    @Override
    public boolean test(Passenger passenger) {
        return passenger.getDriver().isPresent() == isAssigned;
    }
}
