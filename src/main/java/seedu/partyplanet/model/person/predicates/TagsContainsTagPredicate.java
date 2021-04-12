package seedu.partyplanet.model.person.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag}s matches the tag given.
 */
public class TagsContainsTagPredicate implements Predicate<Person> {
    private final String tag;

    public TagsContainsTagPredicate(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(tag -> tag.tagName.toLowerCase().contains(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsTagPredicate // instanceof handles nulls
                && tag.equals(((TagsContainsTagPredicate) other).tag)); // state check
    }

}
