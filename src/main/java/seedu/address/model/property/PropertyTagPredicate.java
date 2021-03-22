package seedu.address.model.property;

import seedu.address.model.tag.Tag;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Tag} contains the given {@code tag}
 */
public class PropertyTagPredicate implements Predicate<Property> {
    private final Tag tag;

    /**
     * Constructs a {@code PropertyTagPredicate}
     * @param tagName String of tag to be compared against
     */
    public PropertyTagPredicate(String tagName) {
        this.tag = new Tag(tagName);
    }

    @Override
    public boolean test(Property property) {
        return property.getTags().contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTagPredicate // instanceof handles nulls
                && tag.equals(((PropertyTagPredicate) other).tag)); // state check
    }

}
