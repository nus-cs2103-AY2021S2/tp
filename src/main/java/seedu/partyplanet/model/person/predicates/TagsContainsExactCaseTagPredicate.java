package seedu.partyplanet.model.person.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag}s contains an exact, case-sensitive match of the tag given.
 */
public class TagsContainsExactCaseTagPredicate implements Predicate<Person> {
    private final String tag;

    public TagsContainsExactCaseTagPredicate(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(tag -> tag.tagName.equals(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainsExactCaseTagPredicate // instanceof handles nulls
                && tag.equals(((TagsContainsExactCaseTagPredicate) other).tag)); // state check
    }

}
