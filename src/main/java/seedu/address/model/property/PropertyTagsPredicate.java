package seedu.address.model.property;

import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Tags} contains the tags given.
 */
public class PropertyTagsPredicate implements Predicate<Property> {
    private final List<Tag> tags;

    public PropertyTagsPredicate(String keyword) {
        String[] keywords = keyword.split(",");
        this.tags = new ArrayList<>();
        for (String s : keywords) {
            this.tags.add(new Tag(s));
        }

    }

    @Override
    public boolean test(Property property) {
        return property.getTags().containsAll(tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTagsPredicate // instanceof handles nulls
                && tags.equals(((PropertyTagsPredicate) other).tags)); // state check
    }

}
