package seedu.student.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Faculty} matches the keyword given.
 */
public class FacultyContainsKeywords implements Predicate<Student> {
    private final String keyword;

    public FacultyContainsKeywords(String keywords) {
        this.keyword = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keyword.equals((student.getFaculty().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FacultyContainsKeywords // instanceof handles nulls
                && keyword.equals(((FacultyContainsKeywords) other).keyword)); // state check
    }

}
