package seedu.address.model.property.status;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a buyer's offer price for a property.
 * Guarantees: immutable; is valid as declared in {@link #isValidOffer(String)}.
 */
public class Offer implements Comparable<Offer> {
    public static final String MESSAGE_CONSTRAINTS = "Note the following conditions for specifying an offer:\n"
            + "1. The dollar sign is optional.\n"
            + "2. There should not be any leading zeros in the number specified.\n"
            + "3. The asking price should not be negative.\n"
            + "4. Either do not use commas at all or be consistent in the usage of commas throughout, "
            + "where each comma should separate every three digits from the back.\n"
            + "E.g.\n"
            + "$1000000 or $1,000,000 are valid but $1000,000 or $100,00,00 are not valid.";

    /*
     * Dollar sign is optional.
     * No leading zeros.
     * Either no commas at all or consistent usage of commas throughout.
     */
    public static final String VALIDATION_REGEX = "\\$?(?<offer>([1-9]\\d*|0|[1-9]\\d{0,2}(,\\d{3})*))";
    public static final Pattern PRICE_FORMAT = Pattern.compile(VALIDATION_REGEX);

    /*
     * Prints the offer as a nicely formatted string with a dollar sign
     * and separates every 3 digits with a comma.
     */
    private static final String OFFER_STRING = "$%,d";

    public final Long offer;

    /**
     * Constructs an {@code Offer}.
     *
     * @param offer A valid offer price.
     */
    public Offer(String offer) {
        requireNonNull(offer);
        checkArgument(isValidOffer(offer), MESSAGE_CONSTRAINTS);
        this.offer = parse(offer);
    }

    /**
     * Returns true if a given string is a valid offer price.
     *
     * @param test The string to test.
     * @return True if the given string is a valid offer price, otherwise false.
     */
    public static boolean isValidOffer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the value of a valid offer string.
     *
     * @param offerString A valid offer string.
     * @return an integer representing the value of the offer.
     * @see #isValidOffer
     */
    public static long parse(String offerString) {
        final Matcher matcher = PRICE_FORMAT.matcher(offerString);
        checkArgument(matcher.matches(), MESSAGE_CONSTRAINTS);
        String amount = matcher.group("offer");
        String amountWithoutCommas = amount.replace(",", "");
        return Long.parseLong(amountWithoutCommas);
    }

    @Override
    public String toString() {
        return String.format(OFFER_STRING, offer);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Offer)) {
            return false;
        }
        Offer otherAskingPrice = (Offer) other;
        return offer == otherAskingPrice.offer;
    }

    @Override
    public int compareTo(Offer another) {
        return offer.compareTo(another.offer);
    }
}
