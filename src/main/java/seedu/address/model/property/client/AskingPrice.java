package seedu.address.model.property.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a client's asking price for a property.
 * Guarantees: immutable; is valid as declared in {@link #isValidAskingPrice(String)}.
 */
public class AskingPrice implements Comparable<AskingPrice> {
    public static final String MESSAGE_CONSTRAINTS = "Note the following conditions for specifying an asking price:\n"
            + "1. The dollar sign is optional.\n"
            + "2. There should not be any leading zeros in the number specified.\n"
            + "3. The cents part is optional but if specified, it has to be exactly 2 decimal places.\n"
            + "4. Either do not use commas at all or be consistent in the usage of commas throughout, "
            + "where each comma should separate every three digits from the back.\n"
            + "   E.g.\n"
            + "   $1000000 or $1,000,000 are valid but $1000,000 or $100,00,00 are not valid.";
    /*
     * Dollar sign is optional.
     * No leading zeros.
     * Either do not specify decimal portion or specify exactly two decimal places.
     * Either no commas at all or consistent usage of commas throughout.
     */
    public static final String VALIDATION_REGEX = "\\$?([1-9]\\d*|0|[1-9]\\d{0,2}(,\\d{3})*)(\\.\\d{2})?";

    public final String askingPrice;

    /**
     * Constructs an {@code AskingPrice}.
     *
     * @param askingPrice A valid asking price.
     */
    public AskingPrice(String askingPrice) {
        requireNonNull(askingPrice);
        checkArgument(isValidAskingPrice(askingPrice), MESSAGE_CONSTRAINTS);
        this.askingPrice = askingPrice;
    }

    /**
     * Returns true if a given string is a valid asking price.
     *
     * @param test The string to test.
     * @return True if the given string is a valid asking price, otherwise false.
     */
    public static boolean isValidAskingPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return askingPrice;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AskingPrice)) {
            return false;
        }
        AskingPrice otherAskingPrice = (AskingPrice) other;
        return askingPrice.equals(otherAskingPrice.askingPrice);
    }

    @Override
    public int compareTo(AskingPrice another) {
        Float price1 = Float.parseFloat(this.askingPrice.substring(1).replace(",", ""));
        Float price2 = Float.parseFloat(another.askingPrice.substring(1).replace(",", ""));
        return price1.compareTo(price2);
    }

    @Override
    public int hashCode() {
        return askingPrice.hashCode();
    }
}
