package seedu.address.model.contact.predicate;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Contact}'s {@code Name} matches any of the keywords given.
 */
public class TagsMatchKeywordPredicate implements Predicate<Contact> {
    private final Set<Tag> tagSet;

    public TagsMatchKeywordPredicate(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public boolean test(Contact contact) {
        return tagSet.stream()
                .anyMatch(tag ->
                        contact.getTags().contains(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsMatchKeywordPredicate // instanceof handles nulls
                && tagSet.equals(((TagsMatchKeywordPredicate) other).tagSet)); // state check
    }
}
