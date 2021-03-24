package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.lesson.Lesson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class HasLessonPredicate implements Predicate<Person> {
    private final Person person;

    public HasLessonPredicate(Person person) {
        this.person = person;
    }

    @Override
    public boolean test(Person person) {
        ArrayList<Lesson> lessonList = new ArrayList<>(person.getLessons());
        return lessonList.isEmpty() ? false : true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HasLessonPredicate // instanceof handles nulls
                && person.equals(((HasLessonPredicate) other).person)); // state check
    }
}
