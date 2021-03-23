package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Matriculation Number} matches any of the keywords given.
 */
public class MatriculationNumberContainsKeywordsPredicate implements Predicate<Student> {
    private final String keyword;

    public MatriculationNumberContainsKeywordsPredicate(String keywords) {
        this.keyword = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keyword.equals((student.getMatriculationNumber().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumberContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((MatriculationNumberContainsKeywordsPredicate) other).keyword)); // state check
    }

}
