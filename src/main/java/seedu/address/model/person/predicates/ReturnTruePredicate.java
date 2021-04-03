package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * A dummy predicate that returns true all the time.
 */
public class ReturnTruePredicate implements Predicate<Person> {
    @Override
    public boolean test(Person person) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ReturnTruePredicate;
    }
}
