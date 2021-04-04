package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that whether a {@code Person} is blacklisted.
 */
public class PersonBlacklistedPredicate implements Predicate<Person> {
    private final boolean isBlacklisted;

    public PersonBlacklistedPredicate(boolean isBlacklisted) {
        this.isBlacklisted = isBlacklisted;
    }

    @Override
    public boolean test(Person person) {
        return person.getBlacklist().isBlacklisted == isBlacklisted;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonBlacklistedPredicate // instanceof handles nulls
                && isBlacklisted == ((PersonBlacklistedPredicate) other).isBlacklisted); // state check
    }
}
