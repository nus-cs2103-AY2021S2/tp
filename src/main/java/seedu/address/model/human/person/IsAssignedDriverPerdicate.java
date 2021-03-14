package seedu.address.model.human.person;

import seedu.address.model.human.driver.Driver;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that whether a {@code Person} is assigned {@code Driver} matches the status given.
 */
public class IsAssignedDriverPerdicate implements Predicate<Person> {
    private final boolean isAssigned;

    public IsAssignedDriverPerdicate(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    @Override
    public boolean test(Person person) {
        return person.getDriver().isPresent() == isAssigned;
    }
}
