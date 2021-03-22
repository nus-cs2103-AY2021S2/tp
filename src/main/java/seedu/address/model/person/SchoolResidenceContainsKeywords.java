package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Residence} matches the keyword given.
 */
public class SchoolResidenceContainsKeywords implements Predicate<Person> {
    private final String keyword;

    public SchoolResidenceContainsKeywords(String keywords) {
        this.keyword = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals((person.getSchoolResidence().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SchoolResidenceContainsKeywords // instanceof handles nulls
                && keyword.equals(((SchoolResidenceContainsKeywords) other).keyword)); // state check
    }

}
