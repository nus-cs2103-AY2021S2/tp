package seedu.address.model.person.predicate;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TagsMatchKeywordPredicate implements Predicate<Person> {
    private final Set<Tag> tagSet;

    public TagsMatchKeywordPredicate(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public boolean test(Person person) {
        return tagSet.stream()
                .anyMatch(tag ->
                        person.getTags().contains(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsMatchKeywordPredicate // instanceof handles nulls
                && tagSet.equals(((TagsMatchKeywordPredicate) other).tagSet)); // state check
    }
}
