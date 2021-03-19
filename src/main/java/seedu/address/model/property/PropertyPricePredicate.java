package seedu.address.model.property;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Name} matches any of the keywords given.
 */
public class PropertyPricePredicate implements Predicate<Property> {
    private final int price;
    private final boolean isLess;

    /**
     * Constructs a {@code PropertyPricePredicate}
     * @param price Value to be compared against
     * @param isLess Whether this predicate is a less than comparison,
     *               note that regardless of this value, any askingPrice
     *               that is equals to the given price will return true
     */
    public PropertyPricePredicate(String price, boolean isLess) {
        this.price = Integer.parseInt(price);
        this.isLess = isLess;
    }

    @Override
    public boolean test(Property property) {
        if (property.getAskingPrice() == null) {
            return false;
        }
        NumberFormat format = NumberFormat.getCurrencyInstance();
        int otherPrice;
        try {
            otherPrice = format.parse(property.getAskingPrice().askingPrice).intValue();
        } catch (ParseException e) {
            return false;
        }

        if (isLess) {
            return otherPrice <= this.price;
        } else {
            return otherPrice >= this.price;
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
