package seedu.partyplanet.model.person.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    public NameContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        return person.getName().fullName.toLowerCase().contains(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
