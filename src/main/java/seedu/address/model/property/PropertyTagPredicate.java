package seedu.address.model.property;

import seedu.address.model.tag.Tag;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Client} {@code AskingPrice} is within the range given.
 */
public class PropertyTagPredicate implements Predicate<Property> {
    private final Tag tag;

    /**
     * Constructs a {@code PropertyPricePredicate}
     * @param price Value to be compared against
     * @param isLess Whether this predicate is a less than comparison,
     *               note that regardless of this value, any askingPrice
     *               that is equals to the given price will return true
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
