package seedu.address.model.person;

import java.util.function.Predicate;

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
