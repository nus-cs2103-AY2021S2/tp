package seedu.address.model.property.status;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a buyer's offer price for a property.
 * Guarantees: immutable; is valid as declared in {@link #isValidOffer(String)}.
 */
public class Offer implements Comparable<Offer> {
    public static final String MESSAGE_CONSTRAINTS = "Note the following conditions for specifying an offer:\n"
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

    public final String offer;

    /**
     * Constructs an {@code AskingPrice}.
     *
     * @param offer A valid asking price.
     */
    public Offer(String offer) {
        requireNonNull(offer);
        checkArgument(isValidOffer(offer), MESSAGE_CONSTRAINTS);
        this.offer = offer;
    }

    /**
     * Returns true if a given string is a valid asking price.
     *
     * @param test The string to test.
     * @return True if the given string is a valid asking price, otherwise false.
     */
    public static boolean isValidOffer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return offer;
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
        return offer.equals(otherAskingPrice.offer);
    }

    @Override
    public int compareTo(Offer another) {
        return this.offer.compareTo(another.offer);
    }

    @Override
    public int hashCode() {
        return offer.hashCode();
    }
}
