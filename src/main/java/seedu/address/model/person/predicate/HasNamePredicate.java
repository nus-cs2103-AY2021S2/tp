package seedu.address.model.person.predicate;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class HasNamePredicate implements Predicate<Person> {

    public HasNamePredicate() {
    }

    @Override
    public boolean test(Person person) {
        return person.getName() == null ? false : true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasLessonPredicate); // instanceof handles nulls
    }

}


