package seedu.booking.model.person;

import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} contains the tag given.
 */
public class PersonTagContainsKeywordsPredicate implements Predicate<Person> {
    private final Tag tag;

    public PersonTagContainsKeywordsPredicate(String tag) {
        this.tag = new Tag(tag);
    }

    public String getTagName() {
        return tag.getTagName();
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(tag -> tag.isSameTag(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof PersonTagContainsKeywordsPredicate)
                && tag.equals(((PersonTagContainsKeywordsPredicate) other).tag));
    }

}
