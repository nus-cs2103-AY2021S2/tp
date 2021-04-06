package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.tag.Tag.MESSAGE_CONSTRAINTS;
import static seedu.address.model.tag.Tag.isValidTagName;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Property}'s {@code Tags} contains the tags given.
 */
public class PropertyTagsPredicate implements Predicate<Property> {
    private final List<String> tags;

    /**
     * Creates a PropertyTagsPredicate
     */
    public PropertyTagsPredicate(String keyword) throws IllegalArgumentException {
        requireNonNull(keyword);
        String[] keywords = keyword.split(",");
        this.tags = new ArrayList<>();
        for (String s : keywords) {
            this.tags.add(s);
            checkArgument(isValidTagName(s), MESSAGE_CONSTRAINTS);
        }

    }

    @Override
    public boolean test(Property property) {
        List<Tag> tested = new ArrayList<>(property.getTags());
        for (String t : tags) {
            if (tested.stream().noneMatch(x -> x.tagName.toLowerCase().contains(t.toLowerCase()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyTagsPredicate // instanceof handles nulls
                && tags.equals(((PropertyTagsPredicate) other).tags)); // state check
    }

}
