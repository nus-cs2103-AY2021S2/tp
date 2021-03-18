package seedu.partyplanet.model.person.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag}s contains an exact match of the tag given.
 */
public class TagsContainsExactTagPredicate implements Predicate<Person> {
    private final String tag;

    public TagsContainsExactTagPredicate(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(tag -> tag.tagName.equalsIgnoreCase(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsExactTagPredicate // instanceof handles nulls
                && tag.equals(((TagsContainsExactTagPredicate) other).tag)); // state check
    }

}
