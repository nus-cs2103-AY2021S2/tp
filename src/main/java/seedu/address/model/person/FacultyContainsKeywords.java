package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Faculty} matches the keyword given.
 */
public class FacultyContainsKeywords implements Predicate<Person> {
    private final String keyword;

    public FacultyContainsKeywords(String keywords) {
        this.keyword = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals((person.getFaculty().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FacultyContainsKeywords // instanceof handles nulls
                && keyword.equals(((FacultyContainsKeywords) other).keyword)); // state check
    }

}
