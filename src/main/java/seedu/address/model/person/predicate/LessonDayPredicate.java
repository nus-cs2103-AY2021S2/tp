package seedu.address.model.person.predicate;

import java.util.function.Predicate;

import seedu.address.model.lesson.Lesson;

/**
 * Tests that a {@code Person}'s {@code Lesson} matches the keyword given.
 */
public class LessonDayPredicate implements Predicate<Lesson> {
    private final String keyword;

    public LessonDayPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Lesson lesson) {
        assert this.keyword != null;
        return lesson.getDayInString().compareToIgnoreCase(keyword) == 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonDayPredicate // instanceof handles nulls
                && keyword.equals(((LessonDayPredicate) other).keyword)); // state check
    }

}
