package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.property.client.AskingPrice;

/**
 * Tests that a {@code Property}'s {@code Client}'s {@code AskingPrice} is within the range given.
 */
public class PropertyPricePredicate implements Predicate<Property> {
    private final int price;
    private final boolean isLess;

    /**
     * Constructs a {@code PropertyPricePredicate}.
     *
     * @param price Value to be compared against.
     * @param isLess Whether this predicate is a less than comparison,
     *               note that regardless of this value, any askingPrice
     *               that is equals to the given price will return true.
     */
    public PropertyPricePredicate(String price, boolean isLess) {
        requireNonNull(price);
        checkArgument(AskingPrice.isValidAskingPrice(price));
        this.price = AskingPrice.parse(price);
        this.isLess = isLess;
    }

    @Override
    public boolean test(Property property) {
        if (property.getAskingPrice() == null) {
            return false;
        }

        if (isLess) {
            return property.getAskingPrice().askingPrice <= price;
        } else {
            return property.getAskingPrice().askingPrice >= price;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyPricePredicate // instanceof handles nulls
                && price == ((PropertyPricePredicate) other).price
                && isLess == ((PropertyPricePredicate) other).isLess); // state check
    }

}
