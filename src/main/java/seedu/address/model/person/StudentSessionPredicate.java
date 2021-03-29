package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class StudentSessionPredicate implements Predicate<Person> {
    private final List<PersonId> studentList;

    public StudentSessionPredicate(List<PersonId> studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean test(Person person) {
        return studentList.contains(person.getPersonId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentSessionPredicate // instanceof handles nulls
                && studentList.equals(((StudentSessionPredicate) other).studentList)); // state check
    }
}
