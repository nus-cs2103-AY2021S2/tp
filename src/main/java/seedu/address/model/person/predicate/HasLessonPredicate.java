package seedu.address.model.person.predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

public class HasLessonPredicate implements Predicate<Person> {

    public HasLessonPredicate() {
    }

    @Override
    public boolean test(Person person) {
        ArrayList<Lesson> lessonList = new ArrayList<>(person.getLessons());
        return lessonList.isEmpty() ? false : true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasLessonPredicate); // instanceof handles nulls
    }

}

