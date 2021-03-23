package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Matriculation Number} matches any of the keywords given.
 */
public class MatriculationNumberContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    public MatriculationNumberContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals((person.getMatriculationNumber().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumberContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((MatriculationNumberContainsKeywordsPredicate) other).keyword)); // state check
    }

}
