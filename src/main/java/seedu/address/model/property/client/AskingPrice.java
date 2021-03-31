package seedu.address.model.property.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a client's asking price for a property.
 * Guarantees: immutable; is valid as declared in {@link #isValidAskingPrice(String)}.
 */
public class AskingPrice implements Comparable<AskingPrice> {
    public static final String MESSAGE_CONSTRAINTS = "Note the following conditions for specifying an asking price:\n"
            + "1. The dollar sign is optional.\n"
            + "2. There should not be any leading zeros in the number specified.\n"
            + "3. Either do not use commas at all or be consistent in the usage of commas throughout, "
            + "where each comma should separate every three digits from the back.\n"
            + "   E.g.\n"
            + "   $1000000 or $1,000,000 are valid but $1000,000 or $100,00,00 are not valid.";
    /*
     * Dollar sign is optional.
     * No leading zeros.
     * Either no commas at all or consistent usage of commas throughout.
     */
    public static final String VALIDATION_REGEX = "\\$?(?<amount>([1-9]\\d*|0|[1-9]\\d{0,2}(,\\d{3})*))";
    public static final Pattern PRICE_FORMAT = Pattern.compile(VALIDATION_REGEX);

    /*
     * Prints the asking price as a nicely formatted string with a dollar sign
     * and separates every 3 digits with a comma.
     */
    private static final String ASKING_PRICE_STRING = "$%,d";

    public final Long askingPrice;

    /**
     * Constructs an {@code AskingPrice}.
     *
     * @param askingPrice A valid asking price.
     */
    public AskingPrice(String askingPrice) {
        requireNonNull(askingPrice);
        checkArgument(isValidAskingPrice(askingPrice), MESSAGE_CONSTRAINTS);
        this.askingPrice = parse(askingPrice);
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

    /**
     * Returns the value of a valid asking price string.
     *
     * @param askingPriceString A valid asking price string.
     * @return an integer representing the value of the price.
     * @see #isValidAskingPrice
     */
    public static Long parse(String askingPriceString) {
        final Matcher matcher = PRICE_FORMAT.matcher(askingPriceString);
        checkArgument(matcher.matches(), MESSAGE_CONSTRAINTS);
        String amount = matcher.group("amount");
        String amountWithoutCommas = amount.replace(",", "");
        return Long.parseLong(amountWithoutCommas);
    }

    @Override
    public String toString() {
        return String.format(ASKING_PRICE_STRING, askingPrice);
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
    public int compareTo(AskingPrice other) {
        return askingPrice.compareTo(other.askingPrice);
    }

}
