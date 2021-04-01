package seedu.address.model.person;

import java.util.function.Predicate;

public abstract class PersonPredicate implements Predicate<Person> {

    @Override
    public abstract boolean test(Person person);

    @Override
    public abstract boolean equals(Object other);
}
