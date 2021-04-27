package seedu.student.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Matriculation Number} matches any of the keywords given.
 */
public class StudentContainsMatriculationNumberPredicate implements Predicate<Student> {
    private final MatriculationNumber keyword;

    public StudentContainsMatriculationNumberPredicate(MatriculationNumber keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {

        return keyword.equals((student.getMatriculationNumber()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentContainsMatriculationNumberPredicate // instanceof handles nulls
                && keyword.equals(((StudentContainsMatriculationNumberPredicate) other).keyword)); // state check
    }

    public MatriculationNumber getKeyword() {
        return keyword;
    }
}
