package seedu.address.model.property;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Property}'s {@code Tags} contains the tags given.
 */
public class PropertyTagsPredicate implements Predicate<Property> {
    private final List<Tag> tags;

    /**
     * Creates a PropertyTagsPredicate
     */
    public PropertyTagsPredicate(String keyword) throws IllegalArgumentException {
        String[] keywords = keyword.split(",");
        this.tags = new ArrayList<>();
        for (String s : keywords) {
            this.tags.add(new Tag(s.strip()));
        }

    }

    @Override
    public boolean test(Property property) {
        List<Tag> tested = new ArrayList<>(property.getTags());
        for (Tag t : tags) {
            if (tested.contains(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTagsPredicate // instanceof handles nulls
                && tags.equals(((PropertyTagsPredicate) other).tags)); // state check
    }

}
