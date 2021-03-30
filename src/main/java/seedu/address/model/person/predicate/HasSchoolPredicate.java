package seedu.address.model.person.predicate;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class HasSchoolPredicate implements Predicate<Person> {

    public HasSchoolPredicate() {
    }

    @Override
    public boolean test(Person person) {
        return person.getSchool().isPresent() ? true : false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasLessonPredicate); // instanceof handles nulls
    }

}
