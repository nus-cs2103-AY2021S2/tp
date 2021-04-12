package seedu.booking.model.person;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} contains any of the tag keywords given.
 */
public class PersonTagContainsKeywordsPredicate implements Predicate<Person> {
    private final Set<Tag> tagSet;

    public PersonTagContainsKeywordsPredicate(Set<Tag> tagSet) {
        this.tagSet = new HashSet<Tag>(tagSet);
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(tagSet::contains);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof PersonTagContainsKeywordsPredicate)
                && tagSet.equals(((PersonTagContainsKeywordsPredicate) other).tagSet));
    }

}
