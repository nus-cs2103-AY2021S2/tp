package seedu.address.model.person.predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.subject.Subject;

public class HasSubjectPredicate implements Predicate<Person> {

    public HasSubjectPredicate() {
    }

    @Override
    public boolean test(Person person) {
        ArrayList<Subject> subjectList = new ArrayList<>(person.getSubjects());
        return subjectList.isEmpty() ? false : true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasLessonPredicate); // instanceof handles nulls
    }

}
