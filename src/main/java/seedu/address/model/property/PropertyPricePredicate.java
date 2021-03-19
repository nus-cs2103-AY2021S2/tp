package seedu.address.model.property;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Name} matches any of the keywords given.
 */
public class PropertyPricePredicate implements Predicate<Property> {
    private final int price;
    private final boolean isLess;

    public PropertyPricePredicate(int price, boolean isLess) {
        this.price = price;
        this.isLess = isLess;
    }

    @Override
    public boolean test(Property property) {
        if (isLess) {
            return Integer.parseInt(property.getAskingPrice().askingPrice) <= this.price;
        } else {
            return Integer.parseInt(property.getAskingPrice().askingPrice) >= this.price;
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
