package seedu.address.model.person;

import java.util.function.Predicate;

public class SamePersonPredicate implements Predicate<Person> {
    private final Person person;

    public SamePersonPredicate(Person person) {
        this.person = person;
    }
    @Override
    public boolean test(Person person) {
        return this.person.equals(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SamePersonPredicate // instanceof handles nulls
                && person.equals(((SamePersonPredicate) other).person)); // state check
    }
}
