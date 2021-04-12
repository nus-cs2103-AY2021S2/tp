package seedu.partyplanet.model.remark;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

/**
 * Represents a remark in PartyPlanet.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {

    public static final String MESSAGE_CONSTRAINTS = "Remark can take any values, and it should not be blank";

    /*
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String EMPTY_REMARK_STRING = "";
    public static final Remark EMPTY_REMARK = new Remark();
    public final String value;
    private boolean isEmpty = false;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        value = remark;
    }

    /**
     * Constructs an empty Remark.
     */
    private Remark() {
        value = EMPTY_REMARK_STRING;
        isEmpty = true;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given remark is an empty remark.
     */
    public static boolean isEmptyRemark(Remark remark) {
        return remark.isEmpty;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Remark // instanceof handles nulls
            && value.equals(((Remark) other).value)) // state check
            && isEmpty == ((Remark) other).isEmpty; // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
