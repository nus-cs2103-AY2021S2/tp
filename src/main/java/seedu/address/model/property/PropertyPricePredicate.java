package seedu.address.model.property;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.model.property.client.AskingPrice;

/**
 * Tests that a {@code Property}'s {@code Client} {@code AskingPrice} is within the range given.
 */
public class PropertyPricePredicate implements Predicate<Property> {
    private final int priceInt;
    private final int priceDecimal;
    private final boolean isLess;

    /**
     * Constructs a {@code PropertyPricePredicate}
     * @param price Value to be compared against
     * @param isLess Whether this predicate is a less than comparison,
     *               note that regardless of this value, any askingPrice
     *               that is equals to the given price will return true
     */
    public PropertyPricePredicate(String price, boolean isLess) {
        checkArgument(AskingPrice.isValidAskingPrice(price));
        int[] priceParts = parseCurrency(price);
        this.priceInt = priceParts[0];
        this.priceDecimal = priceParts[1];
        this.isLess = isLess;
    }

    private int parseIntPart(String firstPart) {
        if (firstPart.contains("$")) {
            firstPart = firstPart.substring(1);
        }
        if (firstPart.contains(",")) {
            firstPart = Arrays.stream(firstPart.split(","))
                              .reduce((a, b) -> a + b)
                              .get();
        }
        return Integer.parseInt(firstPart);
    }

    private int parseCents(String secondPart) {
        return Integer.parseInt(secondPart);
    }

    private int[] parseCurrency(String price) {
        int[] priceParts = new int[2];
        if (price.contains(".")) {
            String[] priceComponents = price.split(".");
            priceParts[0] = parseIntPart(priceComponents[0]);
            priceParts[1] = parseCents(priceComponents[1]);
        } else {
            priceParts[0] = parseIntPart(price);
        }
        return priceParts;
    }

    @Override
    public boolean test(Property property) {
        if (property.getAskingPrice() == null) {
            return false;
        }
        // NumberFormat format = NumberFormat.getCurrencyInstance();
        int[] otherPrice;
        try {
            otherPrice = parseCurrency(property.getAskingPrice().askingPrice);
        } catch (NumberFormatException e) {
            return false;
        }

        if (isLess) {
            return otherPrice[0] < this.priceInt
                    || (otherPrice[0] == this.priceInt
                    && otherPrice[1] <= this.priceDecimal);
        } else {
            return otherPrice[0] > this.priceInt
                    || (otherPrice[0] == this.priceInt
                    && otherPrice[1] >= this.priceDecimal);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyPricePredicate // instanceof handles nulls
                && priceDecimal == ((PropertyPricePredicate) other).priceDecimal
                && priceInt == ((PropertyPricePredicate) other).priceInt
                && isLess == ((PropertyPricePredicate) other).isLess); // state check
    }

}
