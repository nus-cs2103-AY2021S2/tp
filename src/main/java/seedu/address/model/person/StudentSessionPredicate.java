package seedu.address.model.person;

import java.util.function.Predicate;

public class StudentSessionPredicate implements Predicate<Person> {
    private final UniquePersonList studentList;

    public StudentSessionPredicate(UniquePersonList studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean test(Person person) {
        return studentList.contains(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentSessionPredicate // instanceof handles nulls
                && studentList.equals(((StudentSessionPredicate) other).studentList)); // state check
    }
}
