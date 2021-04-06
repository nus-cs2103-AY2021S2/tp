package seedu.booking.model.booking;

import java.util.function.Predicate;

import seedu.booking.model.Tag;
import seedu.booking.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code tags} contains the tag given
 */
public class PersonContainsTagPredicate implements Predicate<Person> {
    private final Tag tag;

    public PersonContainsTagPredicate(String tagName) {
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
                || ((other instanceof PersonContainsTagPredicate)
                && tag.equals(((PersonContainsTagPredicate) other).tag));
    }
}
