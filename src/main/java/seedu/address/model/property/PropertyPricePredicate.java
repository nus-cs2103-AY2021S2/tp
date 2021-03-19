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
        int otherPrice = -1;
        try {
            otherPrice = format.parse(property.getAskingPrice().askingPrice).intValue();
        } catch (ParseException e) {
            return false;
        }
        if (isLess) {
            return  otherPrice <= this.price;
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
