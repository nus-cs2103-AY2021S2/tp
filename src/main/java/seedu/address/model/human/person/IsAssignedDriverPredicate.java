package seedu.address.model.human.person;

import java.util.function.Predicate;

/**
 * Tests that whether a {@code Person} is assigned {@code Driver} matches the status given.
 */
public class IsAssignedDriverPredicate implements Predicate<Person> {
    private final boolean isAssigned;

    public IsAssignedDriverPredicate(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    @Override
    public boolean test(Person person) {
        return person.getDriver().isPresent() == isAssigned;
    }
}
