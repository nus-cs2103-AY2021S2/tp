package seedu.booking.model.person;

import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Venue}'s {@code tags} contains the tag given
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final Tag tag;

    public TagContainsKeywordsPredicate(String tagName) {
        this.tag = new Tag(tagName);
    }

    public String getTagName() {
        return this.tag.getTagName();
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(this.tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof TagContainsKeywordsPredicate)
                && tag.equals(((TagContainsKeywordsPredicate) other).tag));
    }

}
