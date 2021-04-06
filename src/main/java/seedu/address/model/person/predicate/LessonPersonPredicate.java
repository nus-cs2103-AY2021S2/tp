package seedu.address.model.person.predicate;

import java.util.function.Predicate;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Lesson} matches the keyword given.
 */
public class LessonPersonPredicate implements Predicate<Lesson> {
    private final Person person;

    public LessonPersonPredicate(Person person) {
        this.person = person;
    }

    @Override
    public boolean test(Lesson lesson) {
        assert this.person != null;
        return lesson.getPersonSet().contains(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonPersonPredicate // instanceof handles nulls
                && person.equals(((LessonPersonPredicate) other).person)); // state check
    }

}

