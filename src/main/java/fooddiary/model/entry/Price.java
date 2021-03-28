package fooddiary.model.entry;

import static java.util.Objects.requireNonNull;

import fooddiary.commons.util.AppUtil;

/**
 * Represents a Entry's price range in the food diary.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {

    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain a integer (e.g. 7) between 0-999";
    public static final String PRICE_RANGE_DASH = "-";
    public static final String VALIDATION_REGEX = "^[0-9]{1,3}([" + PRICE_RANGE_DASH + "][0-9]{1,3})?$";
    public final String value;

    /**
     * Constructs a {@code price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        AppUtil.checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        if (!test.contains(PRICE_RANGE_DASH)) {
            return true;
        }
        int lowerBoundPrice = Integer.parseInt(test.split(PRICE_RANGE_DASH)[0]);
        int upperBoundPrice = Integer.parseInt(test.split(PRICE_RANGE_DASH)[1]);
        return (lowerBoundPrice < upperBoundPrice);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
