package seedu.partyplanet.model.person.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches all of the keywords given.
 * Match is case-insensitive to account for fact that two names stored in alternate
 * case are semantically the same to the user.
 */
public class NameContainsExactKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    public NameContainsExactKeywordsPredicate(String keywords) {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        return person.getName().fullName.toLowerCase().equals(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsExactKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsExactKeywordsPredicate) other).keywords)); // state check
    }

}
